package mine.learn.multithread;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSONObject;

class A implements Callable<JSONObject>{
    private int n;

    A(int n) {
        this.n = n;
    }

    @Override
    public JSONObject call() throws Exception {
        JSONObject json = new JSONObject();
        HashMap<Integer,Double> map = new HashMap<>();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            map.put(r.nextInt(n), Math.pow(Math.exp(Math.pow(Math.PI, Math.E)), 10.911));
        }
        Thread.sleep(10000);
        json.put("data", map);
        return json;
    }

}