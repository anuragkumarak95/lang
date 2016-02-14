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

    private String type,name;
    private Object value;

    public VariableBlock(Block superBlock,String type,String name,Object value) {
        super(superBlock);
        this.name = name;
        this.type = type;
        this.value = value;
    }

    @Override
    public void run() {
        Type t = Type.match(type);

        if(t == BuiltInType.VOID) throw new IllegalStateException("variable cannot have builtInType : void");

        //add the variable to superBlock as the Variable block is of no use other than Composite class declaration.
        getSuperBlock().addVariable(new Variable(getSuperBlock(), t,name,value));
    }
}
