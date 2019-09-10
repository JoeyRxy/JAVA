package mine.learn.springboot.springconfig.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Point
 */
@PropertySource(value = "classpath:point.properties")
@Component
@ConfigurationProperties(prefix = "point")
public class Point {

    private double x, y;
    private Double r, theta;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getTheta() {
        return theta;
    }

    public void setTheta(Double theta) {
        this.theta = theta;
    }

    @Override
    public String toString() {
        return "Point [r=" + r + ", theta=" + theta + ", x=" + x + ", y=" + y + "]";
    }

}