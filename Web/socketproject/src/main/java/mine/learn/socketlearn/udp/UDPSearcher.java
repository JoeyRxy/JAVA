package mine.learn.socketlearn.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * UDPSearcher: 监听、 回送
 */
public class UDPSearcher {
    private final static int PORT = 2077;

    public static void main(String[] args) throws IOException {
        System.out.println("Searcher Started");

        // 作为搜索方不提供端口
        // TODO 为什么？
        DatagramSocket ds = new DatagramSocket();
        // 发送数据
        // BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String sentStr = MessageCreator.buildWithPort(PORT);
        byte[] sentData = sentStr.getBytes();
        DatagramPacket sentPacket = new DatagramPacket(sentData, sentData.length);

        // 给谁发送？
        sentPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        sentPacket.setPort(2020);// 发送给对面的哪个端口
        ds.send(sentPacket);
        // 接收实体
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);

        // 接收
        ds.receive(receivePack);// This method blocks until a datagram is received
        // 作为接收者：
        String ip = receivePack.getAddress().getHostAddress();// 发送者的ip
        int port = receivePack.getPort();// 发送者的端口
        String data = new String(receivePack.getData());

        System.out.println("UDPSearcher received from ip: " + ip + "\tport: " + port + "\tdata: " + data);
        // 回送给发送者数据

        System.out.println("UDPSearcher finish");
        ds.close();
    }

    private static class Device {

    }

    private static class Listener extends Thread {
        private final int listenPort;
        private final CountDownLatch countDownLatch;
        private final List<Device> devices = new ArrayList<>();
        private final boolean done = false;
        private DatagramSocket ds = null;

        public Listener(int listenPort, CountDownLatch countDownLatch, DatagramSocket ds) {
            this.listenPort = listenPort;
            this.countDownLatch = countDownLatch;
            this.ds = ds;
        }

        @Override
        public void run() {
            super.run();

            countDownLatch.countDown();

            try {

            } catch (Exception e) {

            }
        }

    }
}