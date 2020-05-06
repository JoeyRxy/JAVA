package mine.learn.multithread;

import java.util.HashMap;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;

public class B implements Runnable {
    private int n;
    private JSONObject json;

    /**
     * @return the json
     */
    public JSONObject getJson() {
        return json;
    }

    B(int n) {
        this.n = n;
        this.json = new JSONObject();
    }

    @Override
    public void run() {
        HashMap<Integer, Double> map = new HashMap<>();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            map.put(r.nextInt(n), Math.pow(Math.exp(Math.pow(Math.PI, Math.E)), 10.911));
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        json.put("data", map);
    }

}