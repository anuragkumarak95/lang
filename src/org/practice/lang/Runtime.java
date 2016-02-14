package org.practice.lang;


import com.sun.javaws.exceptions.InvalidArgumentException;
import org.practice.lang.block.Block;
import org.practice.lang.block.Class;
import org.practice.lang.block.Method;
import org.practice.lang.block.VariableBlock;
import org.practice.lang.parser.ClassParser;
import org.practice.lang.parser.MethodParser;
import org.practice.lang.parser.Parser;
import org.practice.lang.parser.VariableParser;
import org.practice.lang.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Runtime application for LANG code.
 */
public class Runtime {

    public static void main(String[] args){
        new Runtime();
    }

    private ArrayList<Class> classes;
    public Runtime(){
        this.classes = new ArrayList<>();
        String code = "class HelloWorld\n" +
                "    method main requires () returns void\n" +
                "var string str = \"hello!\" ";


        boolean success = false;

        Parser<?>[] parsers = new Parser<?>[]{new ClassParser(),new MethodParser(),new VariableParser()};

        Class main = null;

        Block block = null;

        for(String line : code.split("\n")){
            line = line.trim(); // reducing empty spaces.
            success = false;
            Tokenizer tokenizer = new Tokenizer(line);
            for(Parser<?> parser : parsers){
                if(parser.shouldParse(line)){
                    System.out.println(parser.getClass().toString());
                    Block newBlock = parser.parse(block,tokenizer);
                    if(newBlock instanceof Class){
                        classes.add((Class) newBlock);
                        System.out.println(((Class) newBlock).getName());
                    }
                    else if(newBlock instanceof Method){
                        block.addBlock(newBlock);
                    }
                    else if(newBlock instanceof VariableBlock) block.addBlock(newBlock);
                    block = newBlock;
                    success=true;
                    break;

                }
            }


            if(!success) throw new IllegalStateException("Parser Procedure not complete."+line);
        }


        for(Class c : classes){
            for(Block b : c.getSubBlocks()){
                if(b instanceof Method){
                    Method method = (Method) b;
                    if(method.getName().equals("main")&&method.getType().equals("void")&&method.getParams().length==0){main = c;}
                    for(Block var : method.getSubBlocks()){
                        if(var instanceof VariableBlock) System.out.println("Var found.");
                    }
                }

            }
        }

        if(main==null) throw new IllegalStateException("No main class found.");

        main.run();
    }
}
