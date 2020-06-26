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
import java.util.Map;

public class RestClient {

    /**
     * GET Method (without passing Headers) - Status code, Payload & Headers from a GET API call
     */
    public CloseableHttpResponse get(String url) throws IOException {

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

        return httpResponse;
    }

    /**
     * GET Method (while passing Headers using HashMap)
     */
    public CloseableHttpResponse get(String url, HashMap<String, String> headers) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        for (Map.Entry<String, String> entry : headers.entrySet()){
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        return httpResponse;
    }
}


