package org.practice.lang.parser;

import org.practice.lang.Type;
import org.practice.lang.block.Block;
import org.practice.lang.Variable;
import org.practice.lang.tokenizer.Token;
import org.practice.lang.tokenizer.TokenType;
import org.practice.lang.tokenizer.Tokenizer;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Variable value parser.
 */
public class VariableParser extends Parser<Block> {

    @Override
    public boolean shouldParse(String line) {
        return line.matches("var [a-zA-Z]+ [a-zA-Z]+ = \"?[a-zA-Z0-9]\"?");
    }

    @Override
    public Block parse(Block superBlock, Tokenizer tokenizer) {
        tokenizer.nextToken();//skip the var keyword.

        Type type = Type.valueOf(tokenizer.nextToken().getToken().toUpperCase());
        String name = tokenizer.nextToken().getToken();

        tokenizer.nextToken();//skip the assignment operator.

        Token valueToken = tokenizer.nextToken();
        Object value = null;
        //check the Value Type for the aquired token.
        if(valueToken.getType()== TokenType.INTEGER_LITERAL){
            value = Integer.valueOf(valueToken.getToken());
        } else if(valueToken.getType()==TokenType.STRING_LITERAL) {
            value = String.valueOf(valueToken.getToken());
        } else {
            //the value is an identifier, we need to get it's value.
            value = superBlock.getVariable(valueToken.getToken()).getValue();
        }

        //Add this variable to the block.
        superBlock.addVariable(new Variable(superBlock,type,name,value));
        //return nothing.  no block is parsed in this parser, rather a element is added to an old superBlock.
        return null;

    }
}
