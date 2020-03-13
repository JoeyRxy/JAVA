package mine.learn.netprogram.tcp;

import java.io.*;
import java.net.*;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Server
 */
public class Server {

    public static Set<Socket> clientSet;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);// IMPORTANT：需指定端口
        System.out.println("==========Server============");
        System.out.println("running at " + serverSocket.getLocalSocketAddress());
        // ExecutorService pool = Executors.newFixedThreadPool(4);// 暂定最多同时4个线程;好像不太可行
        clientSet = new CopyOnWriteArraySet<>();
        while (true) {
            // new Thread(new ServerHandler(serverSocket)).start();
            Socket client = serverSocket.accept();
            clientSet.add(client);
            new Thread(new ServerHandler()).start();
        }

    }
}

/**
 * 为什么多线程？因为服务器需要同时处理多个 服务请求
 */
class ServerHandler implements Runnable {

    private void deal(Socket client) {
        InetAddress inetAddress = client.getInetAddress();
        int port = client.getPort();
        System.out.println("客户端建立了连接，其地址： " + inetAddress + " ，端口： " + port);
        InputStream inputStream;
        try {
            inputStream = client.getInputStream();
            BufferedReader request = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            OutputStream outputStream = client.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            while (true) {
                String msg = request.readLine();
                System.out.print(inetAddress + "@" + port + " : ");
                System.out.println(msg);
                String reply = "I received " + msg.length() + " words.";
                printStream.println(reply);
            }
        } catch (IOException e) {
            Server.clientSet.remove(client);
        }
    }

    @Override
    public void run() {

        for (Socket client : Server.clientSet) {
            deal(client);
        }
        // try {

        // // int count;
        // while (true) {
        // // 为什么没有不是-1？直接就跳到IOException了？
        // // while (request.read() != -1) {
        // // count++;
        // // }

        // String msg = request.readLine();
        // System.out.print(inetAddress + "@" + port + " : ");
        // System.out.println(msg);

        // // 处理请求，进行服务
        // // 这里的服务是直接返回接收数据的长度……
        // String reply = "I received " + msg.length() + " words.";
        // // outputStream.write(reply.getBytes());
        // printStream.println(reply);
        // }
        // } catch (IOException e) {
        // // e.printStackTrace();
        // System.out.println(inetAddress + "@" + port + " 已断开连接");
        // }
    }
}