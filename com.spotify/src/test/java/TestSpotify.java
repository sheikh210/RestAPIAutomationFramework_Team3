import client.SpotifyRestClient;
import dataProviders.SpotifyDataProviders;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class TestSpotify extends SpotifyRestClient {

    public String endpoint;

    SpotifyRestClient spotify;
    ValidatableResponse response;
    ResponseSpecification checkStatusCodeAndContentType = new ResponseSpecBuilder()
            .expectStatusCode(HttpURLConnection.HTTP_OK)
            .expectContentType(ContentType.JSON).build();

    @BeforeMethod
    public void setUp() {
        spotify = new SpotifyRestClient();
        RestAssured.baseURI = spotify.prop.getProperty("DefaultHost");
    }

    @Test (priority = 1)
    public void testGetPlaylists() {
        response = getAllPlaylists();

        List<String> playlists = response.extract().jsonPath().getList("items.name");
        response.assertThat().spec(checkStatusCodeAndContentType).body("items.name", hasSize(20));

        System.out.println("Playlist Names:\n");
        for (String s : playlists){
            System.out.println(s);
        }
    }

    @Test (priority = 2, dataProvider = "artist_data", dataProviderClass = SpotifyDataProviders.class)
    public void testGetArtists(String artistID, String artistName) {
        response = getArtists(artistID);

        response.assertThat().spec(checkStatusCodeAndContentType).body("artists.name", hasItem(artistName));
        response.extract().response().body().prettyPeek();
    }





}
