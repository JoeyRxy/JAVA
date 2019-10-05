package mine.learn.netprogram.udp.complex;

import java.net.*;
import java.io.*;

/**
 * 
 */
public class UDP1 {

    public static void main(String[] args) throws IOException {
        System.out.println("1 on line");
        // DatagramSocket socket = null;
        // try {
        // socket = new DatagramSocket(6000);// 监听端口
        // } catch (SocketException e) {
        // System.err.println("端口（可能）被占用");
        // e.printStackTrace();
        // }
        // byte[] buf = new byte[60000];
        // DatagramPacket packet = new DatagramPacket(buf, buf.length);//
        // 阻塞式接受包裹，将其存到buf中
        // System.out.println("waiting...");
        // try {
        // socket.receive(packet);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // System.out.println("data is :\n" + new String(buf, 0, packet.getLength()));

        DatagramSocket sendSocket = null, receiveSocket = null;
        try {
            sendSocket = new DatagramSocket(2000);// 本地发送端口
            receiveSocket = new DatagramSocket(6000);// 接收端口
        } catch (SocketException e) {
            System.err.println("端口（可能）被占用");
            e.printStackTrace();
        }
        System.out.println("listening on port: " + receiveSocket.getLocalPort());
        new ReceiveHandler(receiveSocket).start();
        while (true) {
            // IMPORTANT:好像放在一起不行，必须另起一个线程监听信息
            // byte[] buf = new byte[60000];
            // DatagramPacket recivePacket = new DatagramPacket(buf, buf.length);//
            // 阻塞式接受包裹，将其存到buf中
            // System.out.println("waiting...");
            // reciveSocket.receive(recivePacket);
            // System.out.println(new String(buf, 0, recivePacket.getLength()));

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String data = reader.readLine();
            DatagramPacket sendPacket = new DatagramPacket(data.getBytes(), data.length(),
                    new InetSocketAddress("localhost", 9999));// 6000接收端口
            sendSocket.send(sendPacket);
            System.out.println("msg sent.");
        }

        // socket.close();
    }
}