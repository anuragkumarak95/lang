package org.practice.lang;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Represents a Parameter.
 */
public class Parameter {
    private Type type;
    private String name;

    public Parameter(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
