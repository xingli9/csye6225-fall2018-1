package csye6225Web.config;

import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

public class propertyUtil {
    public static String BUCKET_NAME   = "csye6225-fall2018-lixing1.me.csye6225.com";
    public static String ACCESS_KEY;
    public static String SECRET_KEY;
    public static String ENDPOINT = "s3.amazonaws.com";

    private static propertyUtil instance = null;

    private propertyUtil() {
        ResourcePropertySource propertySource2 = null; //name, location
        try {
            propertySource2 = new ResourcePropertySource("resources", "classpath:application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ACCESS_KEY = propertySource2.getProperty("ACCESS_KEY").toString();
        SECRET_KEY = propertySource2.getProperty("SECRET_KEY").toString();

    }

    public static propertyUtil getInstance() {
        if (instance == null)
            instance = new propertyUtil();
        return instance;
    }
}
