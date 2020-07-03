import base.LoadBase;
import client.RestAssuredClient;
import dataProviders.JsonPlaceholderDataProviders;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.*;

public class TestJsonPlaceholder extends RestAssuredClient {

    LoadBase load;
    private String baseURL;
    private String apiURL;
    private String url;
    ValidatableResponse validatableResponse;
    ResponseSpecification checkStatusCodeAndContentType = new ResponseSpecBuilder()
            .expectStatusCode(HttpURLConnection.HTTP_OK)
            .expectContentType(ContentType.JSON).build();


    @BeforeMethod
    public void setUp() {
        load = new LoadBase();
        baseURL = load.prop.getProperty("BaseURL");
    }

    @Test (priority = 1)
    public void testGetAllPosts() {
        apiURL = getENDPOINT_POSTS();
        url = baseURL+apiURL;

        validatableResponse = get(url);
        System.out.println("STATUS CODE: "+validatableResponse.extract().statusCode());

        validatableResponse.assertThat().spec(checkStatusCodeAndContentType).and().body("userId", hasSize(100));
        validatableResponse.extract().response().body().prettyPeek();
    }

    @Test (priority = 2)
    public void testGetAllComments() {
        apiURL = getENDPOINT_COMMENTS();
        url = baseURL + apiURL;

        validatableResponse = get(url);
        validatableResponse.assertThat().spec(checkStatusCodeAndContentType).body("id", hasSize(500));
        validatableResponse.extract().response().body().prettyPeek();
    }

    @Test (priority = 3, dataProvider = "comments_data", dataProviderClass = JsonPlaceholderDataProviders.class)
    public void testGetCommentSelection(int id, String email){
        apiURL = getENDPOINT_COMMENTS() + id;
        url = baseURL + apiURL;

        validatableResponse = get(url);
        validatableResponse.assertThat().spec(checkStatusCodeAndContentType).body("email", hasToString(email));
        validatableResponse.extract().response().body().prettyPeek();
    }

    @Test (priority = 4)
    public void testPostInPost() {
        apiURL = getENDPOINT_POSTS();
        url = baseURL+apiURL;

        HashMap<String, String> jsonBody = new HashMap<>();
        jsonBody.put("title", "TEST POST");
        jsonBody.put("body", "TEST BODY");

        validatableResponse = post(jsonBody, url);
        validatableResponse.assertThat().statusCode(HttpURLConnection.HTTP_CREATED).body("title", hasToString("Sami's Book"));

        validatableResponse.extract().response().body().prettyPeek();
    }

    @Test (priority = 5, dataProvider = "patch_data", dataProviderClass = JsonPlaceholderDataProviders.class)
    public void testPatchInUsers (HashMap map, String id) {
        apiURL = getENDPOINT_USERS() + id;
        url = baseURL + apiURL;

        validatableResponse = patch(map, url);

        String key = "";
        for (Object k : map.keySet()){
            key = String.valueOf(k);
        }

        System.out.println(validatableResponse.extract().statusCode());

        validatableResponse.assertThat().spec(checkStatusCodeAndContentType)
                .and().body(key, hasToString((String) map.get(key)));

        validatableResponse.extract().response().body().prettyPeek();
    }

    @Test (priority = 6, dataProvider = "put_data", dataProviderClass = JsonPlaceholderDataProviders.class)
    public void testPutPosts(HashMap map, String id) {
        apiURL = getENDPOINT_POSTS() + id;
        url = baseURL + apiURL;

        validatableResponse = put(map, url);
        validatableResponse.extract().response().body().prettyPeek();

        String key = null;
        String value = null;

        Set<HashMap.Entry<Object, Object>> entrySet = map.entrySet();
        for (Map.Entry<Object, Object> entry : entrySet){
            key = String.valueOf(entry.getKey());
            value = String.valueOf(entry.getValue());

            validatableResponse.assertThat().spec(checkStatusCodeAndContentType)
                    .and().body(key, hasToString(value));
        }
    }

    @Test (priority = 7, dataProvider = "delete_data", dataProviderClass = JsonPlaceholderDataProviders.class)
    public void testDeleteComments(String id, String expected) {
        apiURL = getENDPOINT_COMMENTS() + id;
        url = baseURL + apiURL;

        validatableResponse = delete(url);

        System.out.println("STATUS CODE: "+validatableResponse.extract().statusCode() + "\n");
        validatableResponse.assertThat().spec(checkStatusCodeAndContentType);

        validatableResponse.extract().response().body().prettyPeek();
        String actualResponseBody = validatableResponse.extract().body().asString();
        Assert.assertEquals(actualResponseBody, expected, "UNEXPECTED RESPONSE");
    }

    @Test (priority = 8)
    public void testHead() {
        apiURL = getENDPOINT_POSTS() + "/1";
        url = baseURL + apiURL;

        validatableResponse = head(url);
        validatableResponse.extract().response().body().prettyPeek();

        validatableResponse.assertThat().spec(checkStatusCodeAndContentType);
    }

    @Test (priority = 9)
    public void testOptions() {
        apiURL = getENDPOINT_USERS();
        url = baseURL + apiURL;

        validatableResponse = options(url);
        validatableResponse.extract().response().body().prettyPeek();
        validatableResponse.assertThat().statusCode(204)
                .header("Access-Control-Allow-Methods", hasToString("GET,HEAD,PUT,PATCH,POST,DELETE"));
    }

}
