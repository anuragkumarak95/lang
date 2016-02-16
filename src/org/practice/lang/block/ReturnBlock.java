package org.practice.lang.block;

import org.practice.lang.BuiltInType;
import org.practice.lang.Value;

/**
 * Created by Anurag on 16-02-2016.
 */
public class ReturnBlock extends Block {

    private final String TAG = "/ReturnParser : ";
    private Value value;

    public ReturnBlock(Block superBlock,Value value) {
        super(superBlock);
        this.value =value;
        init();
    }

    @Override
    public void init() {
        System.out.println(TAG + "return block initialized for method : " + ((Method) getSuperBlock()).getName());
        if(getSuperBlock().getClass() != Method.class) throw new IllegalStateException(TAG+"No method found for the return statement.");

        if(((Method)getSuperBlock()).getType().equals(BuiltInType.VOID.toString().toLowerCase()))
            throw new IllegalStateException("container method doesn't require a return statement.");
    }

    public Value returnValue(){
        return value;
    }
}
