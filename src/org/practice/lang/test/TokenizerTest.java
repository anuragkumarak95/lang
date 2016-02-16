package org.practice.lang.test;

import org.practice.lang.tokenizer.Token;
import org.practice.lang.tokenizer.Tokenizer;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Tokenizer Test Unit
 */
public class TokenizerTest {

    public static void main(String[] args) {
        String code =
                "class HelloWorld\n" +
                        "    method main requires () return void\n" +
                        "array [5] = new integer(1 2 3 4 5)\n" +
                        "        print \"Hello\"";

        Tokenizer tokenizer = new Tokenizer(code);

        while (tokenizer.hasNextToken()) {
            Token token = tokenizer.nextToken();
            System.out.println(token.getToken() + " : " + token.getType());
        }

    }
}
