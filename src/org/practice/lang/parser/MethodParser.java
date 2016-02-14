package org.practice.lang.parser;

import org.practice.lang.BuiltInType;
import org.practice.lang.Parameter;
import org.practice.lang.Type;
import org.practice.lang.block.Block;
import org.practice.lang.block.Method;
import org.practice.lang.tokenizer.Token;
import org.practice.lang.tokenizer.Tokenizer;

import java.util.ArrayList;

/**
 * Created by Anurag on 14-02-2016.
 *
 *
 */
public class MethodParser extends Parser<Method> {



    @Override
    public boolean shouldParse(String line) {
        return line.matches(
                "method [a-zA-z][a-zA-Z0-9]* requires \\(([a-zA-z][a-zA-Z0-9]* [a-zA-z][a-zA-Z0-9]*)*\\) returns [a-zA-z][a-zA-Z0-9]*"
        );
    }

    @Override
    public Method parse(Block superBlock, Tokenizer tokenizer) {

        tokenizer.nextToken(); //skip the method keyword token.
        String name = tokenizer.nextToken().getToken();

        tokenizer.nextToken();//skip requires keyword
        tokenizer.nextToken();//skip opening parentheses '('

        Token first = tokenizer.nextToken();
        ArrayList<Parameter> params = new ArrayList<>();

        //checking where there is _not_an_ end parentheses just after the open parentheses.
        if(!first.getToken().equals(")")){
            String[] paramData = {first.getToken(),null}; // 0 - type, 1 - value

            while(tokenizer.hasNextToken()){
                Token token = tokenizer.nextToken();

                if(token.getToken().equals(")")){break;} // check if the end parentheses has been reached.

                if(paramData[0]==null){
                    paramData[0] = token.getToken();//check whether to add type or value.
                }

                else {
                    paramData[1] = token.getToken();
                    params.add(new Parameter(BuiltInType.valueOf(paramData[0].toUpperCase()),paramData[1])); // in case of value added, add the gathered couple to the array list of parameters.
                    paramData = new String[2];//reset the couple data back to null, for further gathering of parameters.
                }
            }
        }

        tokenizer.nextToken();//skiped the returns keyword.

        String returnType = tokenizer.nextToken().getToken(); // gathered the returnBuiltInType from the token.

        return new Method(superBlock,name, returnType,params.toArray(new Parameter[params.size()]));
    }
}
