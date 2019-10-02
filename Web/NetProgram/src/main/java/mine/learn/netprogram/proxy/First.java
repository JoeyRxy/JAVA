package mine.learn.netprogram.proxy;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * First
 */
public class First extends ProxySelector {

    private List<URI> failed = new ArrayList<>();

    @Override
    public List<Proxy> select(URI uri) {
        List<Proxy> res = new ArrayList<>();
        if (failed.contains(uri) || "http".equalsIgnoreCase(uri.getScheme())) {
            res.add(Proxy.NO_PROXY);
        } else {
            SocketAddress proxyAddress = new InetSocketAddress("proxy.example.com", 8000);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
            res.add(proxy);
        }

        return res;
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        failed.add(uri);
    }

}