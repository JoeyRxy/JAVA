package basis.learn;

import java.io.*;

public class TestIO {
    public static void main(String[] args) throws IOException {
        File file = new File(
                "C:\\Users\\RXY\\Documents\\MYCODE\\JAVA\\testforbasis\\first\\src\\main\\java\\basis\\learn\\shit.txt");
        FileInputStream inputStream = new FileInputStream(file);
        int i = inputStream.read();
        System.out.println((char) i);
        inputStream.close();
    }
}