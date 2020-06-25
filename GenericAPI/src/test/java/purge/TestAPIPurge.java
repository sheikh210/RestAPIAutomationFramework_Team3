package purge;

import base.LoadBase;
import client.RestClient;
import org.testng.annotations.BeforeMethod;

public class TestAPIPurge extends LoadBase {

    private String baseURL;
    private String uri;
    private String url;
    LoadBase load;
    RestClient client;

    @BeforeMethod
    public void setUp() {
        load = new LoadBase();
        baseURL = prop.getProperty("PurgeURL");
        uri = prop.getProperty("PurgeURI");
        url = baseURL + uri;
    }
}
