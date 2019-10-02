package mine.learn.netprogram.thread0;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

/**
 * LogFile
 */
public class LogFile {

    private Writer out;

    public LogFile(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        this.out = new BufferedWriter(fileWriter);
    }

    public void writeEncry(String msg) throws IOException {
        Date date = new Date();
        // 四个单独的写操作，不同步就会出现问题
        synchronized (out) {// 同步this更合适，不过这里一样
            out.write(date.toString());
            out.write('\t');
            out.write(msg);
            out.write("\r\n");
        }

    }

    public void close() throws IOException {
        out.flush();
        out.close();
    }
}