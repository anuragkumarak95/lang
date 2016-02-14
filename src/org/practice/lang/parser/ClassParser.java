package org.practice.lang.parser;

import org.practice.lang.Type;
import org.practice.lang.block.*;
import org.practice.lang.block.Class;
import org.practice.lang.tokenizer.Tokenizer;

/**
 * Created by Anurag on 14-02-2016.
 *
 * The Parser for class block.
 */
public class ClassParser extends Parser<Class> implements Type{
    @Override
    public boolean shouldParse(String line) {
        return line.matches("class [a-zA-Z][a-zA-Z0-9]*");
    }

    @Override
    public Class parse(Block superBlock, Tokenizer tokenizer) {


        tokenizer.nextToken(); // skipping the token class as it is an indicator, and is not needed anymore.
        String name = tokenizer.nextToken().getToken();

        return new Class(name);
    }
}
