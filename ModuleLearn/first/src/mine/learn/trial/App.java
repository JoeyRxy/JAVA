package mine.learn.trial;

import mine.learn.multidownload.Downloader;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class App {
    int x;
    public App(int x){this.x = x;}

    @Override
    public String toString() {
        return "App{" +
                "x=" + x +
                '}';
    }

    public static void main(String[] args) throws URISyntaxException {
        App app = new App(10);
        System.out.println("Hello " + app);

        Downloader downloader = new Downloader(new File("data/test.tif"),new File("tmp"),new URI("https://www.baidu.com"),4,null,null);
    }
}
