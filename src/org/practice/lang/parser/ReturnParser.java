package org.practice.lang.parser;

import org.practice.lang.BuiltInType;
import org.practice.lang.Value;
import org.practice.lang.block.Block;
import org.practice.lang.block.ReturnBlock;
import org.practice.lang.tokenizer.Token;
import org.practice.lang.tokenizer.TokenType;
import org.practice.lang.tokenizer.Tokenizer;

/**
 * Created by Anurag on 16-02-2016.
 *
 * Represents a return block.
 */
public class ReturnParser extends Parser<ReturnBlock>{
    private final String TAG = "/ReturnParser : ";
    @Override
    public boolean shouldParse(String line) {
        return line.matches("return (\")?[a-zA-Z0-9]*(\")?");
    }

    @Override
    public ReturnBlock parse(Block superBlock, Tokenizer tokenizer) {
        tokenizer.nextToken();//skip the return keyword.
        Token token = tokenizer.nextToken();
        if(token.getType()== TokenType.STRING_LITERAL){
            return new ReturnBlock(superBlock,new Value(BuiltInType.STRING,token.getToken()));
        }
        else if(token.getType()==TokenType.INTEGER_LITERAL){
            return new ReturnBlock(superBlock,new Value(BuiltInType.INTEGER,token.getToken()));
        } throw new IllegalStateException(TAG+"unspecified return type at :"+token.getToken()+"("+token.getType()+")");

    }
}
