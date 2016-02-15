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
        return line.matches("print (\")?[a-zA-Z0-9]*(\")?");
    }

    @Override
    public PrintBlock parse(Block superBlock, Tokenizer tokenizer) {

        tokenizer.nextToken();
        String quote = tokenizer.nextToken().getToken();

        return new PrintBlock(superBlock,quote);
    }
}
