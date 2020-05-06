package mine.learn.wsclient;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Client {

    static Session session;

    public static void main(String[] args) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://localhost:8888/ws/websocket";
            System.out.println("Connecting to " + uri);
            container.connectToServer(MyClientEndpoint.class, URI.create(uri));
            session.getBasicRemote().sendText("hello world");
            java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            while (true) {
                String line = r.readLine();
                if (line.equals("quit"))
                    break;
                session.getBasicRemote().sendText(line);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}