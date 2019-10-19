package mine.learn.annotation4j.second;

import java.lang.annotation.*;

/**
 * ExtractInterface
 * <p>
 * 将类中的公共方法提取出来，形成一个接口
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE) // ATTENTION:为什么保留到源代码阶段
public @interface ExtractInterface {

    public String value();
}