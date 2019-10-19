package mine.learn.netprogram.tcp.stream;

import java.io.*;
import java.net.*;

public class Clinet2 {

    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("==========Client============");
        Socket socket = new Socket("localhost", 8888);// Server's addr and port
        socket.setSoTimeout(3000);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // PrintStream printStream = new PrintStream(outputStream);

        String msgToSent = "Hello TCP";
        outputStream.write(msgToSent.getBytes());
        // printStream.println(msgToSent);
        outputStream.flush();

        // read from socket input
        String receivedMsg = new String(new BufferedReader(new InputStreamReader(inputStream)).readLine());
        System.out.println(receivedMsg);

        socket.close();
    }

}