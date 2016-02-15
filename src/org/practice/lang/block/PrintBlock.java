package org.practice.lang.block;

import java.util.IllegalFormatCodePointException;

/**
 * Created by Anurag on 15-02-2016.
 *
 * Repesrents a System print block.
 */
public class PrintBlock extends Block {
    private final String TAG = "PrintBlock/ : ";

    private  final String quote;
    public PrintBlock(Block superBlock,String quote) {
        super(superBlock);
        this.quote = quote;

        run();
    }

    @Override
    public void run() {
        if(getSuperBlock().getClass() != Method.class ) throw new IllegalStateException(TAG+"Print can only called inside a method.");
        System.out.println(TAG+getSuperBlock().toString() + " says : " + quote);
    }
}
