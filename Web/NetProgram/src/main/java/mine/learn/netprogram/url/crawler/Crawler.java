package mine.learn.netprogram.url.crawler;

import java.io.*;
import java.net.*;

/**
 * Crawler + 模拟浏览器
 */
public class Crawler {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.dianping.com/");
        // 无法爬 点评网：使用HTTPURLconnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 模拟浏览器的 GET 请求，详见本project下docs文件夹下的爬虫.md文件
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        InputStream in = connection.getInputStream();
        // Reader reader = new InputStreamReader(new BufferedInputStream(in), "UTF-8");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String msg = null;
        long start = System.currentTimeMillis();
        while ((msg = reader.readLine()) != null) {
            System.out.print(msg);
        }
        long end = System.currentTimeMillis();
        System.out.println("\n" + (end - start));
        reader.close();
    }
}