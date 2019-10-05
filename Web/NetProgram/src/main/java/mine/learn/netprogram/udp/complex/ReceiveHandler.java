package mine.learn.netprogram.udp.complex;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * ReceiveHandler
 */
public class ReceiveHandler extends Thread {

    DatagramSocket receiveSocket;

    @Override
    public void run() {
        while (true) {
            byte[] buf = new byte[60000];
            DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
            // System.out.println("waiting...");
            try {
                receiveSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(new String(buf, 0, receivePacket.getLength()));
            System.out.println(
                    "you've received a msg from : " + receivePacket.getAddress() + "@" + receivePacket.getPort());// IMPORTANT:携带对面信息的是包裹而不是socket~想一想快递员的例子吗？
        }
    }

    public ReceiveHandler(DatagramSocket receiveSocket) {
        this.receiveSocket = receiveSocket;
    }
}