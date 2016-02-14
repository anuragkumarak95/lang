package org.practice.lang.block;

import org.practice.lang.Variable;

import java.util.ArrayList;

/**
 * Created by Anurag on 14-02-2016.
 * <p/>
 * This class is an abstract Parent Block class.
 */
public abstract class Block {
    //containing the parent block of the current block, null in the this case itself.
    private Block superBlock;
    //contains list of sibling blocks.
    private ArrayList<Block> subBlocks;
    private ArrayList<Variable> variables;

    public Block(Block superBlock) {
        this.subBlocks = subBlocks;
        this.subBlocks = new ArrayList<>();
        this.variables = new ArrayList<>();
    }

    public Block getSuperBlock() {
        return superBlock;
    }

    public void addBlock(Block block){
        subBlocks.add(block);
    }

    public void addVariable(Variable variable){
        variables.add(variable);
    }

    /**
     *
     * @param name
     * @return variable for a specific name..
     */
    public Variable getVariable(String name){

        for(Variable variable : variables){
            if (variable.getName().equals(name)){
                return variable;
            }
        }

       //checking for the variable in superBlocks. recursively.
        if(superBlock.getVariable(name)!=null){
            return superBlock.getVariable(name);
        }else

        return null;
    }

    //an abstract method for compulsory inclusion in all the inherited blocks.
    public abstract void run();

}
