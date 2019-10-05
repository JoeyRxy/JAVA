package mine.learn.netprogram.tcp.stream;

import java.io.*;
import java.net.*;

/**
 * Clinet 单纯的字节流能不能胜任？
 */
public class Clinet2 {

    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("==========Client============");
        Socket socket = new Socket("localhost", 8888);// 对应Server的地址和端口
        socket.setSoTimeout(3000);
        // 建立输入流
        InputStream inputStream = socket.getInputStream();
        // 建立输出流
        OutputStream outputStream = socket.getOutputStream();

        // 从键盘输入读取数据
        BufferedReader readerFromSysIn = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String msgToSent = readerFromSysIn.readLine();
        while (!"quit".equals(msgToSent)) {
            outputStream.write(msgToSent.getBytes());

            // 开始接受服务器返回
            byte[] readAllBytes = inputStream.readAllBytes();
            String receivedMsg = new String(readAllBytes);
            System.out.println(receivedMsg);
            msgToSent = readerFromSysIn.readLine();
        }

        socket.close();
    }

}