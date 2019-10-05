package mine.learn.netprogram.tcp;

import java.io.*;
import java.net.*;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;

/**
 * Server
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);// IMPORTANT：需指定端口
        System.out.println("waiting for connection");
        // ExecutorService pool = Executors.newFixedThreadPool(4);// 暂定最多同时4个线程;好像不太可行

        while (true) {
            // new Thread(new ServerHandler(serverSocket)).start();
            Socket client = serverSocket.accept();
            new Thread(new ServerHandler(client)).start();
        }

    }
}

/**
 * 为什么多线程？因为服务器需要同时处理多个 服务请求
 */
class ServerHandler implements Runnable {

    private Socket client;

    ServerHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("客户端建立了连接，其地址： " + client.getInetAddress() + " ，端口： " + client.getPort());
            InputStream inputStream = client.getInputStream();
            BufferedReader request = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            OutputStream outputStream = client.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            // int count;
            while (true) {
                // 为什么没有不是-1？直接就跳到IOException了？
                // while (request.read() != -1) {
                // count++;
                // }

                String msg = request.readLine();
                System.out.println(msg);

                // 处理请求，进行服务
                // 这里的服务是直接返回接收数据的长度……
                String reply = "I received " + msg.length() + " words.";
                // outputStream.write(reply.getBytes());
                printStream.println(reply);
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("已断开连接");
        }
    }
}