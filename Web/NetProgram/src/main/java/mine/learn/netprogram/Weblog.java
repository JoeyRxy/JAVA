package mine.learn.netprogram;

import java.io.*;
import java.net.InetAddress;

/**
 * Weblog
 */
public class Weblog {

    public static void main(String[] args) {
        String filename = "";
        try (FileInputStream fin = new FileInputStream(filename);
                Reader reader = new InputStreamReader(fin);
                BufferedReader bufferedReader = new BufferedReader(reader);) {

            for (String entry = bufferedReader.readLine(); entry != null; entry = bufferedReader.readLine()) {
                int index = entry.indexOf(' ');
                String ip = entry.substring(0, index);
                String rest = entry.substring(index);

                // 向dns请求主机名 并显示
                InetAddress address = InetAddress.getByName(ip);
                System.out.println(address.getHostName() + rest);
            }

        } catch (Exception e) {
        }

    }
}