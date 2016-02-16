package org.practice.lang;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Represents a Value.
 */
public class Value {
    private Type type;
    private Object value;

    public Value(Type type, Object value) {
        this.type = type;
        this.value = value;

    }

    //getters n setters..
    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
