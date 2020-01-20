package basis.learn;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * FilePath
 */
public class FilePath {

    public static void main(String[] args) throws IOException {
        // File file = new File("shit.properties");
        // FileReader reader = new FileReader(file);
        // int c;
        // while ((c = reader.read()) != -1) {
        // System.out.print((char) c);
        // }
        // reader.close();

        InputStream resource = FilePath.class.getClassLoader().getResourceAsStream("shit.properties");
        int c;
        while ((c = resource.read()) != -1) {
            System.out.print((char) c);
        }
        resource.close();
    }
}