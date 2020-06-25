package lock;

import base.LoadBase;
import client.RestClient;
import org.testng.annotations.BeforeMethod;

public class TestAPILock extends LoadBase {

    private String baseURL;
    private String uri;
    private String url;
    LoadBase load;
    RestClient client;

    @BeforeMethod
    public void setUp() {
        load = new LoadBase();
        baseURL = prop.getProperty("LockURL");
        uri = prop.getProperty("LockURI");
        url = baseURL + uri;
    }
}
