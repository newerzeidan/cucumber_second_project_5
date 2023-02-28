package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            // Open file to be read
            FileInputStream fileInputStream = new FileInputStream("config.properties");

            // Read contents
            properties.load(fileInputStream);

            // Close file
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}