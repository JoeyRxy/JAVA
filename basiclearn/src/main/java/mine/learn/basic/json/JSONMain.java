package mine.learn.basic.json;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONMain {
    public static void main(String[] args) {
        Entity entity = Entity.create(10, 100, "");
        JSONArray array = new JSONArray();
        array.add(entity);
        System.out.println(array);

        //
        StringBuilder b = new StringBuilder();

        ArrayList<String> names = new ArrayList<>(array.getJSONObject(0).keySet());
        for (String name : names) {
            b.append(name).append(',');
        }

        b.append('\n');

        int size = array.size();
        for (int i = 0; i < size; ++i) {
            JSONObject obj = array.getJSONObject(i);
            for (String name : names)
                b.append(obj.get(name)).append(',');
            b.append('\n');
        }

        System.out.println(b.toString());

        //

        Field[] fields = entity.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; ++i) {
                System.out.println(fields[i].get(entity).toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
