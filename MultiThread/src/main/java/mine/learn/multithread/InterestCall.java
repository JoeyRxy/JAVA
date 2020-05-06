package mine.learn.multithread;

import java.util.HashMap;
import java.util.concurrent.FutureTask;

import com.alibaba.fastjson.JSONObject;

public class InterestCall {

    public static void main(String[] args) throws Exception {
        A a = new A(100000);
        FutureTask<JSONObject> task = new FutureTask<>(a);
        Thread thread = new Thread(task, "A");
        thread.start();
        JSONObject json = task.get();
        HashMap<Integer,Double> map = (HashMap<Integer,Double>)json.get("data");
        System.out.println(map.size());
        
        // B b = new B(100);
        // Thread thread2 = new Thread(b,"B");
        // thread2.start();
        // JSONObject json2 = b.getJson();
        // System.out.println(json2.get("data"));

        // b.run();
        // JSONObject data = b.getJson();
        // System.out.println(data.get("data"));
    }
}