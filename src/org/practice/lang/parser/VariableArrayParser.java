package org.practice.lang.parser;

import org.practice.lang.block.Block;
import org.practice.lang.block.VariableArrayBlock;
import org.practice.lang.tokenizer.Token;
import org.practice.lang.tokenizer.Tokenizer;

/**
 * Created by Anurag on 16-02-2016.
 * <p>
 * Parser are a variable array.
 */
public class VariableArrayParser extends Parser<VariableArrayBlock> {
    @Override
    public boolean shouldParse(String line) {
        /*
        * Homogeneous Value Array Declaration:
        * <identifier = name>([<value = array_count>]) = new <Type> ([<value>...])
        * TODO : escape [ ] in regular expressions
        */
        return line.matches("[a-zA-Z][a-zA-Z0-9]*\\s*\\([a-zA-Z0-9]+\\)\\s*=\\s*new\\s+[a-zA-Z]+\\s*\\(((\")?[a-zA-Z0-9]*(\")?\\s*)*\\)");
    }

    @Override
    public VariableArrayBlock parse(Block superBlock, Tokenizer tokenizer) {
        String name = tokenizer.nextToken().getToken();
        tokenizer.nextToken();//skip the [
        int vCount = Integer.valueOf(tokenizer.nextToken().getToken());
        tokenizer.nextToken();//skip the ]

        tokenizer.nextToken();//skip the =
        tokenizer.nextToken();//skip the new

        String type = tokenizer.nextToken().getToken();

        tokenizer.nextToken();//skip the (

        Token first = tokenizer.nextToken();
        Object values[] = new Object[vCount];
        int counter = 0;
        if (!first.getToken().equals(")")) {
            while (tokenizer.hasNextToken()) {
                Token token = tokenizer.nextToken();
                if (token.getToken().equals(")")) break;//break out if ) occurs

                values[counter] = token.getToken();
                counter++;
            }
        }

        return new VariableArrayBlock(superBlock, type, name, values);

    }
}
