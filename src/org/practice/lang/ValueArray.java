package org.practice.lang;

/**
 * Created by Anurag on 16-02-2016.
 * <p>
 * Repressents a Value array.
 */
public class ValueArray {
    private Type type;
    private Object[] value;

    //utility functions.
    public ValueArray(Type type, Object[] value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }


    public Object[] getValue() {
        return value;
    }

    public void setValue(Object[] value) {
        this.value = value;
    }
}
