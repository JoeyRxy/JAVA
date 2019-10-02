package mine.learn.netprogram.clients;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

/**
 * Client
 */
public class Client {

    public Client() {

    }

    public static void main(String[] args) {
        try (Socket socket = new Socket("time.nist.gov", 13);) {
            socket.setSoTimeout(3000);
            InputStream in = socket.getInputStream();
            Reader reader = new InputStreamReader(new BufferedInputStream(in), "ASCII");
            StringBuffer buffer = new StringBuffer();
            int c = reader.read();
            while (c != -1) {
                buffer.append((char) c);
                c = reader.read();
            }
            System.out.println(buffer);
        } catch (IOException e) {
            System.err.println("无法连接到");
        }
    }
}