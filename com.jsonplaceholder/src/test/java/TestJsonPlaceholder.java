import base.LoadBase;
import client.RestAssuredClient;
import dataprovider.JsonPlaceholderDataProvider;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;
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
    Response response;
    ValidatableResponse validatableResponse;
    ResponseSpecification checkStatusCodeAndContentType = new ResponseSpecBuilder()
            .expectStatusCode(HttpURLConnection.HTTP_OK)
            .expectContentType(ContentType.JSON).build();


    @BeforeMethod
    public void setUp() {
        load = new LoadBase();
        baseURL = load.prop.getProperty("BaseURL");
    }

    @Test
    public void testGetAllPosts() {
        apiURL = getENDPOINT_POSTS();
        url = baseURL+apiURL;

        response = get(url);
        System.out.println("STATUS CODE: "+response.getStatusCode());

        response.then().assertThat().spec(checkStatusCodeAndContentType).and().body("userId", hasSize(100));
        response.body().prettyPeek();
    }

    @Test
    public void testGetAllComments() {
        apiURL = getENDPOINT_COMMENTS();
        url = baseURL + apiURL;

        response = get(url);
        response.then().assertThat().spec(checkStatusCodeAndContentType).body("id", hasSize(500));
        response.body().prettyPeek();
    }

    @Test (dataProvider = "comments_data", dataProviderClass = JsonPlaceholderDataProvider.class)
    public void testCommentSelection(int id, String email){
        apiURL = getENDPOINT_COMMENTS() + "?id=" + id;
        url = baseURL + apiURL;

        response = get(url);
        response.then().assertThat().spec(checkStatusCodeAndContentType).body("email", hasToString(email));
    }

    @Test
    public void testPostInPost() {
        apiURL = getENDPOINT_POSTS();
        url = baseURL+apiURL;

        HashMap<String, String> jsonBody = new HashMap<>();
        jsonBody.put("title", "Sami's Book");
        jsonBody.put("body", "TEST BODY");

        validatableResponse = post(jsonBody, url);
        validatableResponse.assertThat().statusCode(HttpURLConnection.HTTP_CREATED).body("title", hasToString("Sami's Book"));

        validatableResponse.extract().response().body().prettyPeek();
    }

    @Test (dataProvider = "patch_data", dataProviderClass = JsonPlaceholderDataProvider.class)
    public void testPatchInUsers (HashMap map, String id) {
        apiURL = getENDPOINT_USERS() + id;
        url = baseURL + apiURL;

        validatableResponse = patch(map, url);

        Set keySet = map.keySet();
        String key = "";
        for (Object k : keySet){
            key = String.valueOf(k);
        }

        System.out.println(validatableResponse.extract().statusCode());

        validatableResponse.assertThat().spec(checkStatusCodeAndContentType)
                .and().body(key, hasToString((String) map.get(key)));

        validatableResponse.extract().response().body().prettyPeek();
    }







}
