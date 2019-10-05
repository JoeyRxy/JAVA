package mine.learn.netprogram.udp;

import java.net.*;
import java.io.*;

/**
 * UDPReceiver
 */
public class UDPReceiver {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(6000);// 接收端口
        } catch (SocketException e) {
            System.err.println("端口（可能）被占用");
            e.printStackTrace();
        }
        byte[] buf = new byte[60000];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);// 阻塞式接受包裹，将其存到buf中
        System.out.println("阻塞式接受包裹");
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("data is :\n" + new String(buf, 0, packet.getLength()));
        socket.close();
    }
}