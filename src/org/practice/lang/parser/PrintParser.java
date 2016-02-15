package org.practice.lang.parser;

import org.practice.lang.block.Block;
import org.practice.lang.block.PrintBlock;
import org.practice.lang.tokenizer.Tokenizer;

/**
 * Created by Anurag on 15-02-2016.
 *
 * Represents a system print parser.
 */
public class PrintParser extends Parser<PrintBlock> {
    @Override
    public boolean shouldParse(String line) {
        //in pattern,we use //s+ to specify that this string pattern can have 1-more spaces between that section.
        //here print and the value to print can have 1 - any number of spaces.
        return line.matches("print\\s+(\")?[a-zA-Z0-9]*(\")?");
    }

    @Override
    public PrintBlock parse(Block superBlock, Tokenizer tokenizer) {

        tokenizer.nextToken();
        String quote = tokenizer.nextToken().getToken();

        return new PrintBlock(superBlock,quote);
    }
}
