package org.practice.lang.block;

import org.practice.lang.BuiltInType;
import org.practice.lang.Type;
import org.practice.lang.Variable;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Represents a Variable Block.
 */
public class VariableBlock extends Block {

    private final String TAG = "VariableBlock/ : ";

    private String type,name;
    private Object value;

    public VariableBlock(Block superBlock,String type,String name,Object value) {
        super(superBlock);
        this.name = name;
        this.type = type;
        this.value = value;

        run();
    }

    @Override
    public void run() {
        Type t = Type.match(type);

        if(t == BuiltInType.VOID) throw new IllegalStateException("variable cannot have builtInType : void");

        if(getSuperBlock().getClass() == Class.class) System.out.println("Globar variable : " + name);

        System.out.println(TAG+"var name : "+name+", var type : "+t+", value : "+value);

        //add the variable to superBlock as the Variable block is of no use other than Composite class declaration.
        getSuperBlock().addVariable(new Variable(getSuperBlock(), t,name,value));
    }
}
