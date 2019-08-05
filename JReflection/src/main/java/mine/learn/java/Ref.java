package mine.learn.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * Ref
 */
public class Ref {

    public static void main(String[] args) {
        Class<?> c = App.class;
        String name = c.getName();
        System.out.println(name);
        Method[] declaredMethods = c.getDeclaredMethods();
        Constructor<?> constructor;
        try {
            Field declaredFieldx = c.getDeclaredField("x");
            Field declaredFieldy = c.getDeclaredField("y");
            constructor = c.getConstructor(int.class, int.class);
            Object newInstance = constructor.newInstance(3, 4);
            Method method = c.getMethod("add", int.class, int.class);
            Object result = method.invoke(newInstance, 2, 6);
            declaredFieldy.setAccessible(true);
            declaredFieldy.set(newInstance, 100000000);
            declaredFieldx.setAccessible(true);
            declaredFieldx.set(newInstance, 200000);
            System.out.println(newInstance);
            System.out.println(result);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        Object instance = null;
        try {
            instance = c.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                field.set(instance, (int) (Math.random() * 1000000));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(instance);
        // System.out.println("getDeclaredMethods:");
        // for (Method m : declaredMethods) {
        // System.out.println(m);
        // }

    }
}