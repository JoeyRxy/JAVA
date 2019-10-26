package mine.learn.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Ref
 */
public class Ref {

    public static void main(String[] args) {
        Class<?> clazz = App.class;
        String name = clazz.getName();
        System.out.println("clazz name : " + name);
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Constructor<?> constructor;
        try {
            Field declaredFieldx = clazz.getDeclaredField("x");
            Field declaredFieldy = clazz.getDeclaredField("y");
            // 创建新对象的方法是先建立一个Constructor来建立新对象；通过int.class的方式告诉传入的参数类型 以确定使用哪个构造函数
            constructor = clazz.getConstructor(int.class, int.class);
            Object newInstance = constructor.newInstance(3, 4);
            // 获取方法，并进行参数传递，一般使用18行的方法获得所有declaredMethods
            Method method = clazz.getMethod("add", int.class, int.class);
            Object result = method.invoke(newInstance, 2, 6);
            declaredFieldy.setAccessible(true);
            declaredFieldy.set(newInstance, 100000000);
            declaredFieldx.setAccessible(true);
            declaredFieldx.set(newInstance, 200000);
            System.out.println("the Instance : " + newInstance);
            System.out.println("the invoked method's res : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object instance = null;
        try {
            instance = clazz.getConstructor().newInstance();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();
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