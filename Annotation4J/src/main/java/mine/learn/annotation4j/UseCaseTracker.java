package mine.learn.annotation4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UseCaseTracker
 * <p>
 * 注解处理器
 */
public class UseCaseTracker {

    public static void trackUseCases(List<Integer> useCases, Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            UseCase uc = method.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("Found UseCase: " + uc.id() + " ,DESCRIPTION: " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }

        // 警告
        for (Integer integer : useCases) {
            System.out.println("Warning : Missing use Case-" + integer);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        // useCases.addAll(47,48,49,50);
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }
}