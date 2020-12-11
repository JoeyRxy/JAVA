package rxy.learn.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * FreqAnalysis
 */
public class GnssCN0Analysis {
    // data\data (2).json
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("data/data.json"), StandardCharsets.UTF_8));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null)
            builder.append(line).append('\n');
        reader.close();
        //
        JSONArray array = JSONArray.parseArray(builder.toString());
        builder = null;

        // times
        int len = array.size();
        float[][] cn0OfTimes = new float[Integer.parseInt(array.getJSONObject(len - 1).getString("times"))
                - Integer.parseInt(array.getJSONObject(0).getString("times")) + 1][64];
        int[] size = new int[cn0OfTimes.length];

        for (int i = 0; i < len; ++i) {
            JSONObject obj = array.getJSONObject(i);
            int times = Integer.parseInt(obj.getString("times"));
            int cn0 = Integer.parseInt(obj.getString("mCn0DbHz"));
            if (cn0 > 0) {
                ++cn0OfTimes[times][cn0];
                ++size[times];
            }
        }
        //
        for (int i = 0; i < cn0OfTimes.length; ++i) {
            float[] _cn0Ofi = cn0OfTimes[i];
            int _size = size[i];
            for (int j = 0; j < 64; ++j)
                _cn0Ofi[j] /= _size;
        }
        //
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(new File("data/freq_analysis (4).csv"), StandardCharsets.UTF_8));
        builder = new StringBuilder();
        builder.append("Times").append(',');
        for (int i = 0; i < 64; ++i)
            builder.append(i).append(',');
        builder.append('\n');
        for (int i = 0; i < cn0OfTimes.length; ++i) {
            builder.append(i);
            float[] _cn0 = cn0OfTimes[i];
            for (int j = 0; j < 64; ++j)
                builder.append(',').append(_cn0[j]);
            builder.append('\n');
        }
        writer.write(builder.toString());
        //
        writer.close();
    }
}