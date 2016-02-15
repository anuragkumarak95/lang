package org.practice.lang.block;

import org.practice.lang.Variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
        this.superBlock = superBlock;
        this.subBlocks = new ArrayList<>();
        this.variables = new ArrayList<>();
    }

    public Block getSuperBlock() {
        return superBlock;
    }

    public Block[] getSubBlocks(){
        return subBlocks.toArray(new Block[subBlocks.size()]);
    }

    public void addBlock(Block block){
        subBlocks.add(block);
    }

    public void addVariable(Variable variable){

        //check for uniqueness of the variable identifier.
        for(Variable v : variables){if(v.getName().equals(variable.getName())) throw new IllegalStateException("Variable of same name already present.");}

        variables.add(variable);
    }

    /**
     *
     * @param name
     * @return variable for a specific name..
     */
    public Variable getVariable(String name){

        //TODO : also add type filter for variables or variable of different types could attach to each other.
        for(Variable variable : variables){
            if (variable.getName().equals(name)){
                return variable;
            }
        }
        //checking for the variable in superBlocks. recursively.
        try{return superBlock.getVariable(name);}catch (NullPointerException n){throw new IllegalStateException("No variable initialised of name "+name);}

    }

    /**
     *
     * @return BlockTree of any Block.
     */
    public ArrayList<Block> getBlockTree(){
        ArrayList<Block> blocks = new ArrayList<>();
        Block block = this;
        do{
            blocks.add(block);
            block = block.getSuperBlock();

        }while(block!=null);
        Collections.reverse(blocks);
            return (blocks);
    }

    //variables list getter.
    public ArrayList<Variable> getVariables(){
        return variables;
    }


    //an abstract initialisation method.
    public abstract void init();

}
