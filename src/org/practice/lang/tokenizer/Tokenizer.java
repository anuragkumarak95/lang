package org.practice.lang.tokenizer;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anurag on 14-02-2016.
 * <p/>
 * This is a Custom Tokenizer generated to understand the LANG syntax grammar and tokenize accordingly fom the string document.
 */
public class Tokenizer {
    //contains all token data;
    private ArrayList<TokenData> tokenDatas;

    private String str;

    private Token lastToken;
    private boolean pushBack;

    public Tokenizer(String str) {
        this.str = str;

        this.tokenDatas = new ArrayList<>();
        tokenDatas.add(new TokenData(Pattern.compile("^([a-zA-z][a-zA-z0-9]*)"), TokenType.IDENTIFIER));
        tokenDatas.add(new TokenData(Pattern.compile("^((-)?[0-9]+)"), TokenType.INTEGER_LITERAL));
        //TODO : tokenizer is having problem in token an array if STRING_LITERALS, fix it.
        tokenDatas.add(new TokenData(Pattern.compile("^(\".*\")"), TokenType.STRING_LITERAL));

        //TODO : find a fix for escaping Square Braces. [ ]
        for (String t : new String[]{"=", "(", ")", ".", ",", "["}) {
            tokenDatas.add(new TokenData(Pattern.compile("^(\\Q" + t + "\\E)"), TokenType.TOKEN));
        }
    }

    //function: generate next token from the string input.
    public Token nextToken() {
        str = str.trim();
        //if pushBack is set true, then it will eventually return the last token itself for some unique purpose.
        if (pushBack) {
            pushBack = false;
            return lastToken;
        }

        //if the string input is empty, then it will output the token as a EMPTY type.
        if (str.isEmpty()) {
            return (lastToken = new Token("", TokenType.EMPTY));
        }

        //check the string input for matching patterns from individual TokenData using the Matcher class.
        for (TokenData data : tokenDatas) {
            Matcher matcher = data.getPattern().matcher(str);

            //execute if matcher finds anything.
            if (matcher.find()) {
                //save the content that matched specific tokenData
                String token = matcher.group().trim();
                str = matcher.replaceFirst("");

                //if the token data matched was String_Literal, then remove the double quotes and return the token.
                if (data.getType() == TokenType.STRING_LITERAL) {
                    return (lastToken = new Token(token.substring(1, token.length() - 1), TokenType.STRING_LITERAL));
                } else {
                    //else return the true token itself.
                    return (lastToken = new Token(token, data.getType()));
                }

            }
            //throws a illegalStateException in case no data is matched from the string input.
        }
        throw new IllegalStateException("Could not parse :  " + str);

    }

    //function: check whether the string input has more tokens in it or not.
    public boolean hasNextToken() {
        //if string input is not empty, it has more tokens in it.
        return !str.isEmpty();
    }

    //function: execute the pushback functionality.
    public void pushBack() {
        //if the lastToken has a token in it, only then change the state of pushToken to true.
        if (lastToken != null) {
            this.pushBack = true;
        }
    }

}
