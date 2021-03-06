package org.practice.lang.block;


import org.practice.lang.*;

/**
 * Created by Anurag on 14-02-2016.
 * Represents a Method block.
 */
public class Method extends Block {
    private final String TAG = "MethodBlock/ : ";

    //method name.
    private String name;
    //method return builtInType.
    private String type;
    //method parameters.
    private Parameter[] params;
    //method value, to be used in invoke(), not to be initialized
    private Value returnValue;

    public Method(Block superBlock, String name, String type, Parameter[] params) {
        super(superBlock);
        this.name = name;
        this.type = type;
        this.params = params;
        init();
    }

    @Override
    public void init() {

        System.out.println(TAG + "Name : " + name + " | type " + type + " | parent :" + ((Class) getSuperBlock()).getName());
        //error handling for methods at initialisation.
        Type.match(type);



    }


    public void run() {
        //to start the method functionality, invoke the method.
        returnValue = invoke();
        if(returnValue!=null)
        System.out.println(TAG+"return value is "+returnValue.getValue()+"("+returnValue.getType()+")");


    }

    public Value invoke(Value... values){

        System.out.println(TAG+name+" method called..");
        Type t = Type.match(type);

        //Invoke the method with the supplied values.
        //the number of values invoked is not equal to number of params assigned, then throw an IllegalArgumentException.
        if(values.length != params.length) throw new IllegalArgumentException(TAG+"Wrong number of parameters for the method : "+name);

        for(int i=0;i<values.length&&i<params.length;i++){
            Parameter parameter = params[i];
            Value value = values[i];
            //check fr builtInType mismatch between the params and the invoked values. If so, throw an IllegalStateException.
            if(value.getType()!=parameter.getType())
                throw new IllegalArgumentException(TAG+"parameter Data-builtInType mismatch "+parameter.getName()+" should be "+parameter.getType()+" not "+value.getType());

            //add the value as a variable for this block and its siblings.
            addVariable(new Variable(this, parameter.getType(), parameter.getName(),value.getValue()));

        }

        for(Block b : getSubBlocks()){

            /*
            Add a return block in here and init() the return block to gather the returnValue from it. *done*
            */
            if(b instanceof ReturnBlock){
                this.returnValue = ((ReturnBlock) b).returnValue();
            }
            if(returnValue != null){
                break;
            }
        }

        if(returnValue == null && t != BuiltInType.VOID) throw new IllegalStateException(TAG+"No return value generated. Excepts "+ type);
        Value localReturnValue = returnValue;
        returnValue = null;
        return localReturnValue;
    }

    //getters.
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Parameter[] getParams() {
        return params;
    }

    public Value getReturnValue() {
        return returnValue;
    }
}
