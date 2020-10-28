package mine.learn.basic.json;

import com.alibaba.fastjson.annotation.JSONField;

public class Entity {
    @JSONField(ordinal = 1)
    public int i;
    @JSONField(ordinal = 2)
    public double d;
    @JSONField(ordinal = 3)
    public String s;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Entity() {
    }

    public static Entity create(int i, double d, String s) {
        Entity entity = new Entity();
        entity.i = i;
        entity.d = d;
        entity.s = s == null ? "null" : s;
        return entity;
    }
}
