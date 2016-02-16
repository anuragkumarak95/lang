package org.practice.lang.block;

import org.practice.lang.BuiltInType;
import org.practice.lang.Type;
import org.practice.lang.Variable;
import org.practice.lang.VariableArray;

/**
 * Created by Anurag on 16-02-2016.
 */
public class VariableArrayBlock extends Block {
    private final String TAG = "VariableArrayBlock/ : ";

    private String type, name;
    private Object[] values;

    public VariableArrayBlock(Block superBlock, String type, String name, Object[] values) {
        super(superBlock);
        this.name = name;
        this.type = type;
        this.values = values;

        init();
    }

    @Override
    public void init() {
        Type t = Type.match(type);

        if (t == BuiltInType.VOID) throw new IllegalStateException(TAG + "variable cannot have builtInType : void");

        if (getSuperBlock().getClass() == Class.class) System.out.println(TAG + "Globar variable : " + name);

        System.out.println(TAG + "var name : " + name + ", var type : " + t + ", value : " + values);

        //error handle for uniqueness of any variable.
        for (Variable v : getSuperBlock().getVariables()) {
            if (v.getName().equals(name))
                throw new IllegalStateException("variable with same name exit in the scope. (" + name + ")");
        }

        for (VariableArray v : getSuperBlock().getVariableArrays()) {
            if (v.getName().equals(name))
                throw new IllegalStateException("variable array with same name exit in the scope. (" + name + ")");
        }


        //add the variable to superBlock as the Variable block is of no use other than Composite class declaration.
        getSuperBlock().addVariableArray(new VariableArray(getSuperBlock(), t, name, values));
    }


}
