package org.practice.lang.tokenizer;

/**
 * Created by Anurag on 14-02-2016.
 * <p/>
 * This enumeration contains all the available Token types from LANG syntax grammar.
 */
public enum TokenType {
    //Nothing at all.
    EMPTY,
    //A token for example, ( ) - .
    TOKEN,
    //First character is a letter, any proceeding characters are letters or numbers.
    IDENTIFIER,
    //Any literal that is enclosed in double quotes, "hello" , "2cvy73" , etc.
    STRING_LITERAL,
    //Any Integer.
    INTEGER_LITERAL
}
