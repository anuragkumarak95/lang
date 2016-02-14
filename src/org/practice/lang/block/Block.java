package org.practice.lang.block;

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

    public Block(Block superBlock) {
        this.subBlocks = subBlocks;
        this.subBlocks = new ArrayList<>();
    }

    public Block getSuperBlock() {
        return superBlock;
    }

    public void addBlock(Block block){
        subBlocks.add(block);
    }

    //an abstract method for compulsory inclusion in all the inherited blocks.
    public abstract void run();

}
