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
 * CellInfoDbmAnalysis
 */
public class CellInfoDbmAnalysis {

    public static void main(String[] args) throws IOException {
        File dataFile = new File("data/new cellinfo2.json");
        File retFile = new File("data/new cellinfos_dbmanalysis2.csv");
        BufferedReader reader = new BufferedReader(new FileReader(dataFile, StandardCharsets.UTF_8));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null)
            builder.append(line).append('\n');
        reader.close();
        //
        JSONArray array = JSONArray.parseArray(builder.toString());
        builder = null;

        int len = array.size();
        float[][] dbmOfTimes = new float[Integer.parseInt(array.getJSONObject(len - 1).getString("times"))
                - Integer.parseInt(array.getJSONObject(0).getString("times")) + 1][97];
        int[] cntOfTimes = new int[dbmOfTimes.length];

        //
        for (int i = 0; i < len; ++i) {
            JSONObject obj = array.getJSONObject(i);
            int times = Integer.parseInt(obj.getString("times"));
            try {
                int dbm = Integer.parseInt(obj.getString("dbm"));
                ++dbmOfTimes[times][dbm + 140];
                ++cntOfTimes[times];
            } catch (Exception e) {
                continue;
            }
        }
        //
        for (int i = 0; i < dbmOfTimes.length; ++i) {
            float[] _dbmOfi = dbmOfTimes[i];
            int _total = cntOfTimes[i];
            for (int j = 0; j < 97; ++j)
                _dbmOfi[j] /= _total;
        }
        //
        BufferedWriter writer = new BufferedWriter(new FileWriter(retFile, StandardCharsets.UTF_8));
        builder = new StringBuilder();
        builder.append("Times").append(',');
        for (int i = 0; i < 97; ++i)
            builder.append(i - 140).append(',');
        builder.append('\n');
        for (int i = 0; i < dbmOfTimes.length; ++i) {
            builder.append(i);
            float[] _dbmOfi = dbmOfTimes[i];
            for (int j = 0; j < 97; ++j)
                builder.append(',').append(_dbmOfi[j]);
            builder.append('\n');
        }
        writer.write(builder.toString());
        //
        writer.close();
    }
}