package org.practice.lang.parser;

import org.practice.lang.block.Block;
import org.practice.lang.block.VariableArrayBlock;
import org.practice.lang.tokenizer.Token;
import org.practice.lang.tokenizer.TokenType;
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
        * TODO : tokenizer not working properly for a set of STRING_LITERALS, fix it.
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
            if (!first.getType().toString().toUpperCase().contains(type.toUpperCase()))
                throw new IllegalStateException(first.getToken() + " is not a " + type.toUpperCase());
            values[counter] = first.getToken();
            counter++;
            while (tokenizer.hasNextToken()) {
                Token token = tokenizer.nextToken();
                if (token.getToken().equals(")")) break;//break out if ) occurs

                //TODO : implement variable or variable array assignment to the variable array. *done*
                if (token.getType() == TokenType.IDENTIFIER) {
                    values[counter] = superBlock.getVariable(token.getToken()).getValue();
                    counter++;
                    continue;
                }

                if (!token.getType().toString().toUpperCase().contains(type.toUpperCase()))
                    throw new IllegalStateException(token.getToken() + " is not a " + type.toUpperCase());
                values[counter] = token.getToken();
                counter++;
            }
        }

        return new VariableArrayBlock(superBlock, type, name, values);

    }
}
