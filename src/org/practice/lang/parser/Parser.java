package org.practice.lang.parser;

import org.practice.lang.block.Block;
import org.practice.lang.tokenizer.Tokenizer;

/**
 * Created by Anurag on 14-02-2016.
 *
 *
 */
public abstract class Parser<T extends Block> {

    /**
     *
     * @param line
     * @return boolean: is this the correct element for ths parser.
     */
    public abstract boolean shouldParse(String line);

    /**
     *
     * @param superBlock
     * @param tokenizer
     * @return Block
     */
    public abstract T parse(Block superBlock,Tokenizer tokenizer);
}
