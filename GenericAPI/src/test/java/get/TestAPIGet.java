package get;

import base.LoadBase;
import client.RestClient;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.TestUtilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class TestAPIGet extends LoadBase {

    private String baseURL;
    private String uri;
    private String url;
    LoadBase load;
    RestClient client;
    TestUtilities utility;
    CloseableHttpResponse httpResponse;

    @BeforeMethod
    public void setUp() {
        load = new LoadBase();
        baseURL = prop.getProperty("GetURL");
        uri = prop.getProperty("GetURI");
        url = baseURL + uri;
    }

    @Test
    public void testGet() throws IOException {
        client = new RestClient();
        httpResponse = client.get(url);

        /**
         * Receiving data from GET method
         */

        /* RECEIVING STATUS CODE
         * Receive the status code from response. Since status code is an integer, we store into int variable
         *
         * Assert response code using HttpURLConnection class from Apache
         */
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code: "+statusCode+"\n");
        Assert.assertEquals(statusCode, HttpURLConnection.HTTP_OK, "UNEXPECTED STATUS CODE\n");



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
        System.out.println("Response (JSON):\n"+responsePayloadJSON + "\n");



        utility = new TestUtilities();

        // JSON Object (Single Value) Assertions using getValueByJSONPath() in TestUtilities class
        String per_page = utility.getValueByJSONPath(responsePayloadJSON, "per_page");
        System.out.println("Per Page: " + per_page + "\n");
        Assert.assertEquals(Integer.parseInt(per_page), 6, "UNEXPECTED VALUE - PER PAGE\n");

        String total = utility.getValueByJSONPath(responsePayloadJSON, "total");
        System.out.println("Total: " + total + "\n");
        Assert.assertEquals(Integer.parseInt(total), 12, "UNEXPECTED VALUE - TOTAL\n");

        // JSON Array (Array) Assertions
        String[] dataFirstName = new String[Integer.parseInt(per_page)];

        for (int i = 0; i<dataFirstName.length; i++){
            dataFirstName[i] = utility.getValueByJSONPath(responsePayloadJSON, "data["+i+"]/first_name");
            System.out.println("Data[" + i + "] First Name: " + dataFirstName[i]);
        }
        System.out.println();

        String[] dataLastName = new String[Integer.parseInt(per_page)];
        for (int i = 0; i<dataFirstName.length; i++){
            dataLastName[i] = utility.getValueByJSONPath(responsePayloadJSON, "data["+i+"]/last_name");
            System.out.println("Data[" + i + "] Last Name: " + dataLastName[i]);
        }
        System.out.println();

        String[] dataID = new String[Integer.parseInt(per_page)];
        for (int i = 0; i<dataFirstName.length; i++){
            dataID[i] = utility.getValueByJSONPath(responsePayloadJSON, "data["+i+"]/id");
            System.out.println("Data[" + i + "] ID: " + dataID[i]);
        }
        System.out.println();

        String[] dataAvatar = new String[Integer.parseInt(per_page)];
        for (int i = 0; i<dataFirstName.length; i++){
            dataAvatar[i] = utility.getValueByJSONPath(responsePayloadJSON, "data["+i+"]/avatar");
            System.out.println("Data[" + i + "] Avatar: " + dataAvatar[i]);
        }
        System.out.println();



        /* RECEIVING HEADERS
         * In order to receive the headers, we need to use getAllHeaders() on the response, itself. It will return an
         * array of headers.
         * Since headers are in the form of key-value pairs, we create a Hash Map & store the headers using for-each loop)
         *
         * ***NOTE: Not a common practice to assert on headers
         */
        Header[] headersArray = httpResponse.getAllHeaders();
        HashMap<String, String> headers = new HashMap<String, String>();

        for (Header h:headersArray){
            headers.put(h.getName(), h.getValue());
        }
        System.out.println("Headers:\n"+headers);
    }


}
