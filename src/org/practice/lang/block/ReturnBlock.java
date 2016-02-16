package org.practice.lang.block;

import org.practice.lang.BuiltInType;
import org.practice.lang.Value;

/**
 * Created by Anurag on 16-02-2016.
 */
public class ReturnBlock extends Block {

    private final String TAG = "ReturnParser/ : ";
    private Value value;

    public ReturnBlock(Block superBlock,Value value) {
        super(superBlock);
        this.value =value;
        init();
    }

    @Override
    public void init() {

        //error handling on initialisation of return statement.
        System.out.println(TAG + "return block initialized for method : " + ((Method) getSuperBlock()).getName());
        if(getSuperBlock().getClass() != Method.class) throw new IllegalStateException(TAG+"No method found for the return statement.");

        if (((Method) getSuperBlock()).getType().toUpperCase().equals(BuiltInType.VOID.toString().toUpperCase()))
            throw new IllegalStateException(TAG + "container method doesn't require a return statement.");

        if (!((Method) getSuperBlock()).getType().toUpperCase().equals(value.getType().toString().toUpperCase()))
            throw new IllegalStateException(TAG + "illegal return type exception. Expected return type : " + (((Method) getSuperBlock()).getType()));
    }

    public Value returnValue(){
        return value;
    }
}
