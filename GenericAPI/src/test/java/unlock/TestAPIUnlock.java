package unlock;

import base.LoadBase;
import client.RestClient;
import org.testng.annotations.BeforeMethod;

public class TestAPIUnlock extends LoadBase {

    private String baseURL;
    private String uri;
    private String url;
    LoadBase load;
    RestClient client;

    @BeforeMethod
    public void setUp() {
        load = new LoadBase();
        baseURL = prop.getProperty("UnlockURL");
        uri = prop.getProperty("UnlockURI");
        url = baseURL + uri;
    }
}
