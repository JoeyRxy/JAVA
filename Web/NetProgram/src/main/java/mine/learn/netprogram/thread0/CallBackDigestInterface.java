package mine.learn.netprogram.thread0;

import org.apache.commons.codec.binary.Hex;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CallBackDigestInterface {

    public synchronized static void receiveDigest(byte[] digest, String filename) {
        System.out.print(filename);
        System.out.print(": ");
        System.out.println(Hex.encodeHex(digest));
    }

    public static void main(String[] args) {
        String prefix = "C:\\Users\\Rxy\\Documents\\MYCODE\\LeetCode\\src\\main\\java\\mine\\leetcode\\";
        String[] filenames = { "src\\main\\java\\mine\\learn\\netprogram\\App.java", prefix + "CoinChange.java",
                prefix + "CycleII.java", prefix + "TreeNode.java", prefix + "LargestNum.java" };
        for (var filename : filenames) {
            CallBackDigest callBackDigest = new CallBackDigest(filename);
            Thread t = new Thread(callBackDigest);
            t.start();
        }
    }

}

/**
 * CallBackDigest
 */
class CallBackDigest implements Runnable {
    private String filename;

    @Override
    public void run() {
        try {
            FileInputStream fin = new FileInputStream(filename);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(fin, digest);
            while (din.read() != -1)
                ;
            din.close();
            byte[] res = digest.digest();
            CallBackDigestInterface.receiveDigest(res, filename);// IMPORTANT:在线程结尾回调（callback）创建自己的线程，以达到*返回本线程run()结果*的目的
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CallBackDigest(String filename) {
        this.filename = filename;
    }

}
