package client;

import base.LoadBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class SpotifyRestClient extends LoadBase {

    private final String DEFAULT_HOST;
    private final String AUTH_HOST;
    private final String CLIENT_ID;
    private final String SECRET_CLIENT_ID;
    private final String TOKEN;

    private String ENDPOINT_GET_ARTISTS = "/artists";
    private String ENDPOINT_GET_TRACKS = "/tracks";
    private String ENDPOINT_GET_ALBUMS = "/albums";
    private String ENDPOINT_GET_ALL_PLAYLISTS = "/me/playlists";
    public String getENDPOINT_GET_ARTISTS() { return ENDPOINT_GET_ARTISTS; }
    public String getENDPOINT_GET_TRACKS() { return ENDPOINT_GET_TRACKS; }
    public String getENDPOINT_GET_ALBUMS() { return ENDPOINT_GET_ALBUMS; }
    public String getENDPOINT_GET_ALL_PLAYLISTS() { return ENDPOINT_GET_ALL_PLAYLISTS; }

    private String path = System.getProperty("user.dir")+"\\src\\main\\resources\\spotify.properties";

    public SpotifyRestClient() {
        this.DEFAULT_HOST = prop.getProperty("DefaultHost");
        this.AUTH_HOST = prop.getProperty("AuthHost");
        this.CLIENT_ID = prop.getProperty("ClientID");
        this.SECRET_CLIENT_ID = prop.getProperty("SecretClientID");
        this.TOKEN = prop.getProperty("Token");
    }

    public ValidatableResponse getAllPlaylists() {
        RestAssured.defaultParser = Parser.JSON;

        return given().auth().oauth2(TOKEN).contentType(ContentType.JSON).when().get(ENDPOINT_GET_ALL_PLAYLISTS).then();
    }

    public ValidatableResponse getArtists(String ids) {
        RestAssured.defaultParser = Parser.JSON;

        return given().auth().oauth2(TOKEN).contentType(ContentType.JSON).queryParam("ids", ids).when().get(ENDPOINT_GET_ARTISTS).then();
    }





}
