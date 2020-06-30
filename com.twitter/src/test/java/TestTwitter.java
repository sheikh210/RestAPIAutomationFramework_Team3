import client.TwitterRestClient;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.TestUtilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;

public class TestTwitter extends TwitterRestClient {

    private String expectedWBPath = System.getProperty("user.dir")+"\\src\\test\\resources\\Test_Data.xlsx";
    private String resourcesPath = System.getProperty("user.dir")+"\\src\\main\\resources\\twitter.properties";
    ValidatableResponse response;
    TwitterRestClient twitterRestClient = new TwitterRestClient();
    ResponseSpecification checkStatusCodeAndContentType = new ResponseSpecBuilder()
            .expectStatusCode(HttpURLConnection.HTTP_OK)
            .expectContentType(ContentType.JSON).build();

    @BeforeMethod
    public void setUp() {
        twitterRestClient.loadProp();
    }

    // POST
    @Test (priority = 1)
    @Parameters ("username")
    public void testTwitterFollow(@Optional("realDonaldTrump") String username) {
        response = twitterRestClient.postTwitterFollow(username);

        response.assertThat().spec(checkStatusCodeAndContentType);

        String expectedFollow = response.extract().response().body().jsonPath().getJsonObject("screen_name").toString();
        System.out.println("Followed: " + expectedFollow);
        Assert.assertEquals(username, expectedFollow, "UNABLE TO FOLLOW CORRECT USERNAME");
    }

    // POST
    @Test (priority = 2)
    @Parameters ("username")
    public void testTwitterUnFollow(@Optional("realDonaldTrump") String username) {
        response = twitterRestClient.postTwitterUnFollow(username);

        response.assertThat().spec(checkStatusCodeAndContentType);

        String expectedUnFollow = response.extract().response().body().jsonPath().getJsonObject("screen_name").toString();
        System.out.println("Un-Followed: " + expectedUnFollow);
        Assert.assertEquals(username, expectedUnFollow, "UNABLE TO UN-FOLLOW CORRECT USERNAME");
    }

    // GET
    @Test (priority = 3)
    public void testTwitterFriendsListCount() {
        response = twitterRestClient.getTwitterFriendList("@ssheikh210");

        List<String> responseString = response.extract().response().body().jsonPath().getList("users");
        System.out.println("SIZE OF \"USERS\" IN RESPONSE PAYLOAD: "+responseString.size());

        response.assertThat().spec(checkStatusCodeAndContentType)
                .and().body("users", hasSize(20));
    }

    // GET
    @Test (priority = 4)
    public void testTwitterFriendsListNames() throws IOException {
        response = twitterRestClient.getTwitterFriendList("@ssheikh210");

        response.assertThat().spec(checkStatusCodeAndContentType);

        List<String> usernames = response.extract().body().jsonPath().getJsonObject("users.screen_name");
        String[] actualUsernames = new String[usernames.size()];

        int i = 0;
        System.out.println("USERNAMES:");
        for (String s : usernames) {
            actualUsernames[i] = s;
            System.out.println(s);
            i++;
        }
        Assert.assertTrue(TestUtilities.compareTextListToExpectedStringArray(actualUsernames, expectedWBPath, "@ssheikh_Friends"));
    }

























//        System.out.println(response.extract().response().body().prettyPrint());


    //    @Test
//    public void testStatusCode(){
//        restAssuredClient = new RestAssuredClient();
//        response = restAssuredClient.get(this.url);
//
//        int actualStatusCode = response.getStatusCode();
//        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
//
//        System.out.println("Actual Status Code: "+actualStatusCode);
//    }
//
//    @Test
//    public void testSizeData() {
//        restAssuredClient = new RestAssuredClient();
//        response = restAssuredClient.get(this.url);
//
//        response.then().assertThat().body("data",hasSize(6));
//    }
//
//    @Test
//    public void testResponse() {
//        restAssuredClient = new RestAssuredClient();
//        response = restAssuredClient.get(this.url);
//
//        response.then().assertThat().body("page", is(1))
//                .and().body("per_page", is(6))
//                .and().body("total", is(12))
//                .and().body("total_pages", is(2));
//    }
//
//    @Test
//    public void test() {
//        restAssuredClient = new RestAssuredClient();
//        response = restAssuredClient.get(this.url);
//        System.out.println(response.body().prettyPrint());
//    }
}



