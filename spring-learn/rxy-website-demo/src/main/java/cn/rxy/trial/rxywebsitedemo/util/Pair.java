package cn.rxy.trial.rxywebsitedemo.util;

public class Pair<T1 extends Comparable<?>, T2> {

    private T1 key;
    private T2 val;

    public Pair(T1 key, T2 val) {
        this.key = key;
        this.val = val;
    }

    public T1 getKey() {
        return key;
    }

    public void setKey(T1 key) {
        this.key = key;
    }

    public T2 getVal() {
        return val;
    }

    public void setVal(T2 val) {
        this.val = val;
    }

}
