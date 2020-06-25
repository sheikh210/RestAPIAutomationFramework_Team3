package link;

import base.LoadBase;
import client.RestClient;
import org.testng.annotations.BeforeMethod;

public class TestAPILink extends LoadBase {

    private String baseURL;
    private String uri;
    private String url;
    LoadBase load;
    RestClient client;

    @BeforeMethod
    public void setUp() {
        load = new LoadBase();
        baseURL = prop.getProperty("LinkURL");
        uri = prop.getProperty("LinkURI");
        url = baseURL + uri;
    }
}
