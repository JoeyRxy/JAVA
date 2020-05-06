package mine.learn.wsclient;

import java.io.IOException;

import javax.websocket.*;

@ClientEndpoint
public class MyClientEndpoint {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        try {
            Client.session = session;
            System.out.println(Client.session);
            String name = "Duke";
            System.out.println("Sending message to endpoint: " + name);
            session.getBasicRemote().sendText(name);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void processMessage(String message) {
        System.out.println("Received message in client: " + message);
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }
}