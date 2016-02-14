package org.practice.lang;

import org.practice.lang.Type;
import org.practice.lang.Value;
import org.practice.lang.block.Block;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Represents a Variable Value.
 */
public class Variable extends Value{
    private Block block;
    private String name;

    public Variable(Block block,Type type,String name, Object value) {
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
