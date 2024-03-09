import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public static String getApiKey() {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
            return properties.getProperty("freeCurrencyApiKey");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}