package mine.learn.basic;

public class ParamRef {

    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public ParamRef(int x) {
        this.x = x;
    }

    public static void f(ParamRef x) {
        x.setX(-1);
    }

    public static void main(String[] args) {
        ParamRef ref = new ParamRef(10);
        System.out.println(ref.getX());
        f(ref);
        System.out.println(ref.getX());
    }
}