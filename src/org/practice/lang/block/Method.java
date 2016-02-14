package org.practice.lang.block;


import org.practice.lang.Parameter;
import org.practice.lang.Type;
import org.practice.lang.Value;

/**
 * Created by Anurag on 14-02-2016.
 * Represents a Method block.
 */
public class Method extends Block {

    //method name.
    private String name;
    //method return type.
    private Type type;
    //method parameters.
    private Parameter[] params;
    //method value, to be used in invoke(), not to be initialized
    private Value returnValue;

    public Method(Block superBlock, String name, Type type, Parameter[] params) {
        super(superBlock);
        this.name = name;
        this.type = type;
        this.params = params;
    }

    @Override
    public void run() {
        invoke();
    }

    public void invoke(Value... values){
        //Invoke the method with the supplied values.
    }
}
