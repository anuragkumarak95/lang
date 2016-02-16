package org.practice.lang;

import org.practice.lang.block.Block;

/**
 * Created by Anurag on 16-02-2016.
 * <p>
 * Represents an Array of Variables.
 */
public class VariableArray extends ValueArray {
    private Block block;
    private String name;

    public VariableArray(Block block, Type type, String name, Object[] value) {
        super(type, value);
        this.block = block;
        this.name = name;
    }

    public Block getBlock() {
        return block;
    }

    public String getName() {
        return name;
    }
}
