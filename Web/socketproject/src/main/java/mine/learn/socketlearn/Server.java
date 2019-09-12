package mine.learn.socketlearn;

import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000);
        // 服务器信息
        System.out.println("server waiting...");
        System.out.println("server info:" + serverSocket.getInetAddress() + "\tport:" + serverSocket.getLocalPort());

        while (true) {
            // 等待客户端连接
            Socket clientSocket = serverSocket.accept();
            // 利用Tread构建异步线程：启动客户端
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandler.start();// extends from Tread
        }

    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private boolean flag = false;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("client info:" + socket.getInetAddress() + "\tport:" + socket.getPort());

            try {
                // 往对面output的流
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                // 接受对面的信息流
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do {
                    // 从客户端拿到一条数据
                    String str = socketInput.readLine();
                    if ("bye".equals(str)) {
                        flag = true;
                        // 服务器也回送一条“bye”
                        socketOutput.println("bye");
                    } else {
                        System.out.println(str);
                        // 告诉客户端获得了
                        socketOutput.println("server get " + str.length() + " words.");
                    }
                } while (!flag);
                socketInput.close();
                socketOutput.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("connection failed!");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}