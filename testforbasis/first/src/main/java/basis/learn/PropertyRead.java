package basis.learn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertyRead
 */
public class PropertyRead {

    public PropertyRead() {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream("shit.properties");
        try {
            properties.load(inputStream);
            String property = properties.getProperty("shit");
            System.out.println(property);
            String property2 = properties.getProperty("the");
            System.out.println(property2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PropertyRead();
    }
}