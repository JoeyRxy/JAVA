package mine.learn.netprogram;

import java.io.File;

/**
 * FilesRead
 */
public class FilesRead {

    public static void main(String[] args) {
        String dir = "newfolder";
        File f = new File(dir);
        File[] flist = f.listFiles();
        for (var file : flist) {
            if (!file.isDirectory()) {
                System.out.println("文件： " + file.getName());
            } else {
                System.out.println("文件夹： " + file.getName());
            }
        }
    }
}