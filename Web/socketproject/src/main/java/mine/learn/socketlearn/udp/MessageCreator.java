package mine.learn.socketlearn.udp;

/**
 * MessageCreator
 */
public class MessageCreator {

    private final static String SN_HEADER = "Message received, my id:";
    private final static String PORT_HEADER = "Message received, reply at port:";

    public static int parsePort(String data) {
        if (data.startsWith(PORT_HEADER)) {
            return Integer.parseInt(data.substring(PORT_HEADER.length()));
        }

        return -1;
    }

    public static String buildWithPort(int port) {
        return PORT_HEADER + port;
    }

    public static String buildWithSN(String sn) {
        return SN_HEADER + sn;
    }

    public static String parseSN(String data) {
        if (data.startsWith(SN_HEADER)) {
            return data.substring(SN_HEADER.length());
        }
        return null;
    }

}