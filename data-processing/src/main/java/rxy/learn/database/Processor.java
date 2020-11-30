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
 * Processor
 */
public class Processor {

    public static void main(String[] args) throws IOException {
        String invalid = "∞";

        BufferedReader reader = new BufferedReader(new FileReader(new File("csvjson.json"), StandardCharsets.UTF_8));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line).append('\n');
        }

        reader.close();

        JSONArray data = JSONArray.parseArray(builder.toString());
        int len = data.size();
        JSONArray ret = new JSONArray();
        for (int i = 0; i < len; ++i) {
            JSONObject obj = data.getJSONObject(i);
            String tag = obj.getString("tag");
            if (tag.equalsIgnoreCase("other"))
                continue;
            if (tag.equals("CellInfoLte")) {
                if (obj.getString("mRssi").contains(invalid) || obj.getString("mRsrp").contains(invalid)
                        || obj.getString("mRsrq").contains(invalid) || obj.getString("mRssnr").contains(invalid)
                        || obj.getString("mCqi").contains(invalid)
                        || obj.getString("mTimeingAdvance").contains(invalid))
                    continue;
            }
            if (tag.equals("CellInfoNr")) {
                if (obj.getString("mCsiRsrp").contains(invalid) || obj.getString("mCsiRsrq").contains(invalid)
                        || obj.getString("mCsiSinr").contains(invalid) || obj.getString("mSsRsrp").contains(invalid)
                        || obj.getString("mSsRsrq").contains(invalid) || obj.getString("mSsSinr").contains(invalid))
                    continue;
            }
            ret.add(obj);
        }
        //
        System.out.println("original size: " + len);
        System.out.println("ret size: " + ret.size());

        //
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("ret.json"), StandardCharsets.UTF_8));
        writer.write(ret.toJSONString());
        writer.close();
    }
}