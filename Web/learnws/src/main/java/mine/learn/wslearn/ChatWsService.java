package mine.learn.wslearn;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@ServerEndpoint("/chat/{usrName}")
public class ChatWsService {
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    private String usrName;

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<ChatWsService> webSocketSet = new CopyOnWriteArraySet<ChatWsService>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    public ChatWsService() {
        System.out.println("-----------------------------");
    }

    /**
     * 连接建立成功调用的方法
     * 
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("usrName") String usrName) {
        this.usrName = usrName;
        this.session = session;
        webSocketSet.forEach(new Consumer<ChatWsService>() {

            @Override
            public void accept(ChatWsService t) {
                try {
                    t.sendMessage("新用户：" + usrName + "已加入！\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        webSocketSet.add(this); // 加入set中
        addOnlineCount(); // 在线数加1
        System.out.println("有新连接：" + usrName + "加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     * 
     * @throws IOException
     */
    @OnClose
    public void onClose() throws IOException {
        webSocketSet.forEach(new Consumer<ChatWsService>() {

            @Override
            public void accept(ChatWsService t) {
                try {
                    t.sendMessage("用户" + usrName + "已退出。");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        webSocketSet.remove(this); // 从set中删除
        subOnlineCount(); // 在线数减1
        System.out.println("用户" + this.usrName + "已退出。当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * 
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        // 群发消息
        for (ChatWsService item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 发生错误时调用
     * 
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * 
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(usrName + " : " + message);
        // this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ChatWsService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ChatWsService.onlineCount--;
    }
}