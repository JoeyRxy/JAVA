package mine.learn.netprogram.thread0.execute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

/**
 * GZip
 */
public class GZip {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        String[] filenames = { "newfolder" };

        for (var filename : filenames) {
            File f = new File(filename);
            if (f.exists()) {
                if (f.isDirectory()) {
                    File[] flist = f.listFiles();
                    for (int i = 0; i < flist.length; i++) {
                        if (!flist[i].isDirectory()) {
                            pool.submit(new GZipRunnable(flist[i]));
                        }
                    }
                } else {
                    pool.submit(new GZipRunnable(f));
                }
            }
        }
        pool.shutdown();
    }
}