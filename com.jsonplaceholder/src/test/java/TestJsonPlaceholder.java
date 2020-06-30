import base.LoadBase;
import client.RestAssuredClient;
import dataprovider.JsonPlaceholderDataProvider;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.*;

public class TestJsonPlaceholder extends RestAssuredClient {

    LoadBase load;
    private String baseURL;
    private String apiURL;
    private String url;
    Response response;
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





}
