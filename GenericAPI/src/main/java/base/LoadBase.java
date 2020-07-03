package base;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadBase {

    public Properties prop;
    private String path = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";

    public LoadBase() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(path);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
