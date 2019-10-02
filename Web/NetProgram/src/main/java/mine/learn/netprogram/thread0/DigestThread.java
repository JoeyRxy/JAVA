package mine.learn.netprogram.thread0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * DigestThread
 */
public class DigestThread extends Thread {

    private String filename;

    public DigestThread(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("sha-256");
            DigestInputStream digestInputStream = new DigestInputStream(in, sha);
            while (digestInputStream.read() != -1)
                ;
            digestInputStream.close();
            byte[] digest = sha.digest();

            // 如果不放在一起，就会打印得很乱
            // String res = new String(filename + ": " + Hex.encodeHexString(digest));
            // System.out.println(res);

            synchronized (System.out) {
                System.out.print(filename + ": ");
                System.out.println(Hex.encodeHex(digest));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String prefix = "C:\\Users\\Rxy\\Documents\\MYCODE\\LeetCode\\src\\main\\java\\mine\\leetcode\\";
        String[] filename = { "src\\main\\java\\mine\\learn\\netprogram\\App.java", prefix + "CoinChange.java",
                prefix + "CycleII.java", prefix + "TreeNode.java", prefix + "LargestNum.java" };

        for (var x : filename) {
            DigestThread t = new DigestThread(x);
            t.start();
            System.out.print("thread name: ");
            System.out.println(t.getName());
        }
    }

}