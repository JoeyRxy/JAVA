package basis.learn;

/**
 * GenericArray
 */
public class GenericArray<T> {

    private T val;
    private GenericArray<T>[] next;

    public GenericArray() {
        val = 0;
        next = new GenericArray<T>();
    }
    
}