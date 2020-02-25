package mine.learn.bean;

import java.util.Map;

/**
 * Cars
 */
public class Cars {

    private int price;
    private String name;
    private Map<Integer, Integer> maps;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Integer> getMaps() {
        return maps;
    }

    public void setMaps(Map<Integer, Integer> maps) {
        this.maps = maps;
    }

    public Cars(int price, String name, Map<Integer, Integer> maps) {
        this.price = price;
        this.name = name;
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "Cars [maps=" + maps + ", name=" + name + ", price=" + price + "]";
    }
}