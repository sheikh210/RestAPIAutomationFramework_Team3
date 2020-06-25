package base;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadBase {

    public Properties prop;

    public LoadBase() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                    "\\src\\main\\resources\\config.properties");
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
