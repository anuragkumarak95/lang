package org.practice.lang.block;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Represents a class block.
 */
public class Class extends Block{
    private String name;

    //constructor: as a class doesn't have any parent node, the superClass for this is null.
    public Class(String name) {
        super(null);
        this.name = name;
    }

    @Override
    public void run() {

    }

    public String getName() {
        return name;
    }
}
