package put;

import base.LoadBase;
import client.RestClient;
import org.testng.annotations.BeforeMethod;

public class TestAPIPut extends LoadBase {

    private String baseURL;
    private String uri;
    private String url;
    LoadBase load;
    RestClient client;

    @BeforeMethod
    public void setUp() {
        load = new LoadBase();
        baseURL = prop.getProperty("PutURL");
        uri = prop.getProperty("PutURI");
        url = baseURL + uri;
    }
}
