package mine.learn.netprogram.tcp.stream;

import java.io.*;
import java.net.*;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;

/**
 * Server
 */
public class Server2 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("waiting for connection");

        while (true) {
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

            OutputStream outputStream = client.getOutputStream();

            while (true) {
                byte[] readAllBytes = inputStream.readAllBytes();
                String msg = new String(readAllBytes);
                System.out.println(msg);

                String reply = "I received " + msg.length() + " words.";
                outputStream.write(reply.getBytes());
            }
        } catch (IOException e) {
            System.out.println("已断开连接");
        }
    }
}