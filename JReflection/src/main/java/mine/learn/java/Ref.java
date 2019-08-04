package mine.learn.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Ref
 */
public class Ref {

    public static void main(String[] args) {
        Class<?> c = App.class;
        String name = c.getName();
        System.out.println(name);
        Method[] declaredMethods = c.getDeclaredMethods();
        try {
            Constructor<?> constructor = c.getConstructor(int.class, int.class);
            Object newInstance = constructor.newInstance(3, 4);
            Method method = c.getMethod("add", int.class, int.class);
            Object result = method.invoke(newInstance, 2, 6);
            System.out.println(result);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("getDeclaredMethods:");
        for (Method m : declaredMethods) {
            System.out.println(m);
        }

    }
}