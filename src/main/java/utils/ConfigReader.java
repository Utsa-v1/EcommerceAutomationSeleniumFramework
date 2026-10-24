package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public static void loadProperties(){

        properties = new Properties();

        try (FileInputStream file = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(file);
            // try-with-resources automatically closes the FileInputStream
            // after the try block finishes, even if an exception occurs.
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
