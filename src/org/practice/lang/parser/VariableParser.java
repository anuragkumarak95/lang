package org.practice.lang.parser;

import org.practice.lang.BuiltInType;
import org.practice.lang.Type;
import org.practice.lang.block.Block;
import org.practice.lang.Variable;
import org.practice.lang.block.VariableBlock;
import org.practice.lang.tokenizer.Token;
import org.practice.lang.tokenizer.TokenType;
import org.practice.lang.tokenizer.Tokenizer;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Variable value parser.
 */
public class VariableParser extends Parser<VariableBlock> {

    @Override
    public boolean shouldParse(String line) {
        return line.matches("var [a-zA-Z]+ [a-zA-Z]+ = (\")?[a-zA-Z0-9\\!\\,]*(\")?");
    }

    @Override
    public VariableBlock parse(Block superBlock, Tokenizer tokenizer) {

        tokenizer.nextToken();//skip the var keyword.

        String type = tokenizer.nextToken().getToken();//gathering type

        String name = tokenizer.nextToken().getToken();//gathering name

        tokenizer.nextToken();//skip the assignment operator.

        Token valueToken = tokenizer.nextToken();
        Object value = null;
        //check the Value BuiltInType for the aquired token.
        if(valueToken.getType()== TokenType.INTEGER_LITERAL){
            value = Integer.valueOf(valueToken.getToken());
        } else if(valueToken.getType()==TokenType.STRING_LITERAL) {
            value = String.valueOf(valueToken.getToken());
        } else {
            //the value is an identifier, we need to get it's value.
            value = superBlock.getVariable(valueToken.getToken()).getValue();
        }

        return new VariableBlock(superBlock,type,name,value);

    }
}
