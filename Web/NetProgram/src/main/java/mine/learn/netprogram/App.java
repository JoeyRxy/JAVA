package mine.learn.netprogram;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws UnknownHostException {
        // InetAddress address = InetAddress.getByName("www.baidu.com");
        // System.out.println(address);

        // InetAddress address2 = InetAddress.getByName(address.getHostAddress());
        // System.out.println(address2.getHostName());

        // InetAddress address3 = InetAddress.getLocalHost();
        // System.out.println(address3);

        // InetAddress address = InetAddress.getByName("www.ibiblio.org");
        // System.out.println(address);

        // InetAddress local = InetAddress.getByName("127.0.0.1");
        // try {
        // NetworkInterface networkInterface = NetworkInterface.getByInetAddress(local);
        // System.out.println("the interface : " + networkInterface);
        // } catch (SocketException e) {
        // e.printStackTrace();
        // }

        // 列出所有本地接口
        // try {
        // Enumeration<NetworkInterface> interfaces =
        // NetworkInterface.getNetworkInterfaces();
        // NetworkInterface ni;
        // while (interfaces.hasMoreElements()) {
        // ni = interfaces.nextElement();
        // System.out.println(ni);
        // }
        // } catch (SocketException e) {
        // e.printStackTrace();

        // URL
        try {
            URL url = new URL("https://github.com/JoeyRxy/code/blob/master/computer%20skill/2019-05-14-12-34-54.png");
            try (Reader in = new InputStreamReader(new BufferedInputStream(url.openStream()));
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream("test whyis html not png"));) {
                int c;
                while ((c = in.read()) != -1)
                    out.write(c);
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        //
    }
}
