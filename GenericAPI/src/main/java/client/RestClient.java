package client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class RestClient {

    /**
     * GET Method - Status code, Payload & Headers from a GET API call
     */
    public void get(String url) throws IOException {

        /* createDefault() returns an object of CloseableHttpClient, which is an abstract class
         * With this reference variable (httpClient), we are creating a simple http client
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();

        /* This statement creates a reference variable that will create a "GET" connection with the defined URL being
         * passed in as an argument
         */
        HttpGet httpGet = new HttpGet(url);

        /* 2nd part of the statement = The previously declared ref variable (httpClient) contains a method, execute().
         * This will perform the execution of our method (httpGet) on the specified url that was passed as an
         * argument (try-catch or throws in method signature)
         *
         * 1st part of the statement CloseableHttpResponse (interface) will allow us to receive the response from the
         * execute(), so we store the response in the reference variable, httpResponse.
         * THIS RESPONSE CONTAINS: RESPONSE PAYLOAD, HEADERS & STATUS CODE
         */
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        /* RECEIVING STATUS CODE
         * Receive the status code from response. Since status code is an integer, we store into int variable
         */
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code: "+statusCode+"\n");

        /* RECEIVING RESPONSE PAYLOAD
         * In order to receive the payload from the response, we need to use EntityUtils class & convert it to a String.
         * Pass the httpResponse, in order to receive the response, and call getEntity() on the response.
         * UTF-8 is defined as default character set, returning standard characters, in case of special characters, etc.
         */
        String responsePayload = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        /* We need to convert the String (responsePayload) into JSON format. Using the JSON dependency, we use
         * JSONObject class to create a new ref variable and pass in the responsePayload (string). The string will be
         * converted to a JSON array
         */
        JSONObject responsePayloadJSON = new JSONObject(responsePayload);
        System.out.println("Response (JSON):\n"+responsePayloadJSON);

        /* RECEIVING HEADERS
         * In order to receive the headers, we need to use getAllHeaders() on the response, itself. It will return an
         * array of headers.
         * Since headers are in the form of key-value pairs, we create a Hash Map & store the headers using for-each loop)
         */
        Header[] headersArray = httpResponse.getAllHeaders();
        HashMap<String, String> headers = new HashMap<String, String>();

        for (Header h:headersArray){
            headers.put(h.getName(), h.getValue());
        }
        System.out.println("Headers:\n"+headers);
    }
}
