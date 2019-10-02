package mine.learn.netprogram;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 为什么多线程起到了作用？
 * <p>
 * 单线程的模式为什么慢？因为需要等待读取完网络上的资源之后才能进行处理；
 * <p>
 * 而多线程操作的话，可以让一个线程读取的时候，让已经读取完的先开始执行
 */
public class PooledWeblog {
    private final static int NUM_THREADS = 4;

    public static void main(String[] args) throws IOException {
        String file = "pom.xml";

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        Queue<LogEntry> results = new LinkedList<LogEntry>();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));) {
            for (String entry = in.readLine(); entry != null; entry = in.readLine()) {
                LookupTask task = new LookupTask(entry);
                Future<String> future = executor.submit(task);
                LogEntry result = new LogEntry(entry, future);
                results.add(result);
            }
        }

        for (LogEntry result : results) {
            try {
                System.out.println(result.future.get());
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println(result.original);
            }
        }
        executor.shutdown();
    }

    private static class LogEntry {
        String original;
        Future<String> future;

        LogEntry(String original, Future<String> future) {
            this.original = original;
            this.future = future;
        }
    }
}

class LookupTask implements Callable<String> {
    private String line;

    public LookupTask(String line) {
        this.line = line;
    }

    @Override
    public String call() {
        try {
            // separate out the IP address
            int index = line.indexOf(' ');
            String address = line.substring(0, index);
            String theRest = line.substring(index);
            String hostname = InetAddress.getByName(address).getHostName();
            return hostname + " " + theRest;
        } catch (Exception ex) {
            return line;
        }
    }
}
