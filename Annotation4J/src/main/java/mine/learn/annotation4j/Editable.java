package mine.learn.annotation4j;

/**
 * Editable
 */
public @interface Editable {

    boolean value() default false;
}