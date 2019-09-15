package mine.learn.socketlearn.udp;

import java.io.IOException;
import java.net.*;
import java.util.UUID;

/**
 * UDPProvider 既有接受也有发送（广播）
 */
public class UDPProvider {

    public static void main(String[] args) throws IOException {
        String sn = UUID.randomUUID().toString();
        ProviderHandler handler = new ProviderHandler(sn);
        handler.start();

        System.in.read();
        handler.exit();
    }

    private static class ProviderHandler extends Thread {
        private final String sn;
        private boolean done = false;

        private DatagramSocket ds = null;

        public ProviderHandler(String sn) {
            super();
            this.sn = sn;
        }

        @Override
        public void run() {
            super.run();

            try {
                // 作为接收者，接收消息的端口
                ds = new DatagramSocket(2020);

                while (!done) {
                    System.out.println("Provider Started");

                    // 接收实体
                    final byte[] buf = new byte[512];
                    DatagramPacket receivePack = new DatagramPacket(buf, buf.length);// 这里是DatagramPacket不是上边的DatagramSocket！这个是用来接受数据

                    // 接收
                    ds.receive(receivePack);// This method blocks until a datagram is received

                    // 作为接收者：
                    String ip = receivePack.getAddress().getHostAddress();// 发送者的ip
                    int port = receivePack.getPort();// 发送者的端口
                    String data = new String(receivePack.getData());

                    System.out.println("UDPProvider received from ip: " + ip + "\tport: " + port + "\tdata: " + data);
                    // 回送给发送者数据
                    int responsePort = MessageCreator.parsePort(data);
                    if (responsePort != -1) {
                        String response = MessageCreator.buildWithSN(sn);
                        byte[] responseData = response.getBytes();
                        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);// 这里是DatagramPacket不是上边的DatagramSocket！这个是用来是发送的
                        responsePacket.setAddress(receivePack.getAddress());
                        responsePacket.setPort(responsePort);// 发送给对面的哪个端口
                        ds.send(responsePacket);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();// 在exit()方法之后会关掉ds，这时候这条线程就会出错了，不过无关紧要
            } finally {
                ds.close();
            }

            System.out.println("UDPProvider Finish");
        }

        public void exit() {
            done = true;
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

    }
}