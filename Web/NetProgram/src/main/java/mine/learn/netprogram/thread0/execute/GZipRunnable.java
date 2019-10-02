package mine.learn.netprogram.thread0.execute;

import java.io.*;
import java.util.zip.*;

/**
 * GZipRunnable
 */
public class GZipRunnable implements Runnable {

    private final File input;

    public GZipRunnable(File input) {
        this.input = input;
    }

    @Override
    public void run() {
        if (!input.getName().endsWith(".gz")) {
            File output = new File(input.getParent(), input.getName() + ".gz");
            if (!output.exists()) {
                // InputStream in = new FileInputStream(input);
                try (InputStream in = new BufferedInputStream(new FileInputStream(input));
                        OutputStream out = new BufferedOutputStream(
                                new GZIPOutputStream(new FileOutputStream(output)));) {// try with
                                                                                       // resources:自动关闭资源，不用finally
                    int b;
                    while ((b = in.read()) != -1)
                        out.write(b);
                    out.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}