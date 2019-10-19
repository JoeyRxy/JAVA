package mine.learn.annotation4j.sqlutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DBTable
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {

    public String name() default "";// ATTENTION:使用空字符串等特殊形式来指示“未赋值”状态
}