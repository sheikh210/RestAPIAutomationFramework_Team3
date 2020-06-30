package client;

import io.restassured.response.ValidatableResponse;

import java.io.FileInputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TwitterRestClient {

    private String APIKey;
    private String APISecretKey;
    private String Token;
    private String TokenSecret;
    Properties prop;
    private final String BASE_URL = "https://api.twitter.com/1.1";
    private final String ENDPOINT_CREATE_FOLLOW = "/friendships/create.json";
    private final String ENDPOINT_UNFOLLOW = "/friendships/destroy.json";
    private final String ENDPOINT_FRIENDS_LIST = "/friends/list.json";
    private final String ENDPOINT_TRENDS_LOCATON = "/trends/place.json";
    private String URI;

    public void loadProp() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream((System.getProperty("user.dir")+"\\src\\main\\resources\\twitter.properties"));
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.APIKey = prop.getProperty("APIKey");
        this.APISecretKey = prop.getProperty("APISecretKey");
        this.Token = prop.getProperty("AccessToken");
        this.TokenSecret = prop.getProperty("AccessTokenSecret");
    }

    public ValidatableResponse postTwitterFollow(String username) {
        URI = BASE_URL + ENDPOINT_CREATE_FOLLOW;
        return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret).param("screen_name", username)
                .when().post(URI).then();
    }

    public ValidatableResponse getTwitterFriendList(String userName) {
        URI = BASE_URL + ENDPOINT_FRIENDS_LIST;
        return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret).param("screen_name", userName,
                "skip_status", "true", "include_user_entities", "false").when().get(URI).then();
    }

    public ValidatableResponse postTwitterUnFollow(String username) {
        URI = BASE_URL + ENDPOINT_UNFOLLOW;
        return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret).param("screen_name", username)
                .when().post(URI).then();
    }

    public ValidatableResponse getTwitterTrendsPerLocation(String location){
        URI = BASE_URL + ENDPOINT_TRENDS_LOCATON;
        return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret).param("id", location)
                    .when().get(URI).then();
    }
}
