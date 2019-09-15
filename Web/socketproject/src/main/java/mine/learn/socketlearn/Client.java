package mine.learn.socketlearn;

import java.io.*;
import java.net.*;

/**
 * Clinet
 */
public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        // 客户端
        Socket socket = new Socket();
        // 超时时间
        socket.setSoTimeout(3000);
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2019), 3000);// connet to server,so there
                                                                                       // should be the info of the
                                                                                       // server you want to connect

        System.out.println("sent message");
        System.out.println("client:" + socket.getLocalAddress() + "\tport:" + socket.getLocalPort());
        System.out.println("server:" + socket.getInetAddress() + "\tport:" + socket.getPort());

        /**
         * 模拟流程： 1. 将键盘输入读入 2. 发送给socket(outputstream) 3. 服务器作出反应，并通过socket发送回数据 4.
         * 通过inputstream得到服务器发送的数据
         */

        // 键盘输入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        // 通过socket发送
        OutputStream outputStream = socket.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        // 通过socket接受
        InputStream inputStream = socket.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean flag = false;
        do {
            // 从键盘读取一行
            String str = input.readLine();
            // 通过socket发送到服务器
            printStream.println(str);
            // 从服务器得到一行数据
            String echo = socketBufferedReader.readLine();
            if ("bye".equals(echo)) {
                flag = true;
            } else {
                System.out.println(echo);
            }
        } while (!flag);

        socket.close();
        System.out.println("client has exit");

    }

}