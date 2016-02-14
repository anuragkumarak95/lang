package org.practice.lang.tokenizer;

import java.util.regex.Pattern;

/**
 * Created by Anurag on 14-02-2016.
 * <p/>
 * This class contains the individual Token Patterns (or syntax grammars) used in LANG.
 */
public class TokenData {
    private Pattern pattern;
    private TokenType type;

    public TokenData(Pattern pattern, TokenType type) {
        this.pattern = pattern;
        this.type = type;
    }

    //getters.
    public Pattern getPattern() {
        return pattern;
    }

    public TokenType getType() {
        return type;
    }
}
