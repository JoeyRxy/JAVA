package mine.learn.netprogram.tcp;

import java.io.*;
import java.net.*;

/**
 * Clinet
 */
public class Clinet {

    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("==========Client============");
        Socket socket = new Socket("localhost", 8888);// 对应Server的地址和端口
        // System.out.println("请求连接");
        socket.setSoTimeout(3000);
        // 建立输入流
        InputStream inputStream = socket.getInputStream();
        BufferedReader received = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        // 建立输出流
        OutputStream outputStream = socket.getOutputStream();
        // BufferedWriter request = new BufferedWriter(new
        // OutputStreamWriter(outputStream, "UTF-8"));
        // TODO: 为什么必须是PrintStream？？其他不行吗？
        PrintStream printStream = new PrintStream(outputStream);

        // 从键盘输入读取数据
        BufferedReader readerFromSysIn = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String msgToSent = readerFromSysIn.readLine();
        while (!"quit".equals(msgToSent)) {
            // outputStream.write(msgToSent.getBytes());
            // outputStream.flush();
            // request.write(msgToSent);
            printStream.println(msgToSent);

            // 开始接受服务器返回
            String receivedMsg = received.readLine();
            System.out.println(receivedMsg);
            msgToSent = readerFromSysIn.readLine();
        }
        printStream.println(msgToSent);

        socket.close();
    }

}