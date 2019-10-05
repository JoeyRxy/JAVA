package mine.learn.netprogram.udp;

import java.net.*;
import java.io.*;

/**
 * UDPSend
 */
public class UDPSend {

    public static void main(String[] args) {
        System.out.println("发送服务启动……");
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(1040);// 本地发送端口
        } catch (SocketException e) {
            System.err.println("端口（可能）被占用");
            e.printStackTrace();
        }
        String data = "hello UDP";
        DatagramPacket packet = new DatagramPacket(data.getBytes(), data.length(),
                new InetSocketAddress("localhost", 6000));// 6000接收端口
        // IMPORTANT:为什么是在数据包里指定目标地址？我懂了，像快递员一样，根据快递包裹上的地址发送数据
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
    }
}