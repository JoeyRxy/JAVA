package mine.learn.netprogram.tcp.stream;

import java.io.*;
import java.net.*;

public class Server2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        while (true) {
            Socket client = serverSocket.accept();
            new Thread(new ServerHandler(client)).start();
        }
    }
}

class ServerHandler implements Runnable {
    private Socket client;

    ServerHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            InetAddress clientAddr = client.getInetAddress();
            int clientPort = client.getPort();
            System.out.println("client connected @ " + clientAddr + ":" + clientPort);

            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();

            while (true) {
                String msg = new String(new BufferedReader(new InputStreamReader(inputStream)).readLine());
                System.out.print("/" + clientAddr + "@" + clientPort + " : ");
                System.out.println(msg);

                String reply = "I received " + msg.length() + " words.";// return how many words the server got.
                outputStream.write(reply.getBytes());
                outputStream.flush();// flush to ensure send all msg,but seems doesn't work
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}