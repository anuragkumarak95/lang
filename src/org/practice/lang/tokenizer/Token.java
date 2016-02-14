package org.practice.lang.tokenizer;

/**
 * Created by Anurag on 14-02-2016.
 * <p/>
 * This class contains the standard token model in LANG (having a token string and its Token Type.).
 */
public class Token {
    private String token;
    private TokenType type;

    public Token(String token, TokenType type) {
        this.token = token;
        this.type = type;
    }

    //getters.
    public String getToken() {
        return token;
    }

    public TokenType getType() {
        return type;
    }
}
