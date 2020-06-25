package get;

import base.LoadBase;
import client.RestClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestAPIGet extends LoadBase {

    private String baseURL;
    private String uri;
    private String url;
    LoadBase load;
    RestClient client;

    @BeforeMethod
    public void setUp() {
        load = new LoadBase();
        baseURL = prop.getProperty("GetURL");
        uri = prop.getProperty("GetURI");
        url = baseURL + uri;
    }

    @Test
    public void testGet() throws IOException {
        client.get(url);
    }


}
