package org.practice.lang;


import org.practice.lang.block.*;
import org.practice.lang.block.Class;
import org.practice.lang.parser.*;
import org.practice.lang.tokenizer.Tokenizer;

import java.util.ArrayList;

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


    //constructor: debugger constructor.
    public Runtime(){
        this.classes = new ArrayList<>();
        //testing lang code.
        String code =
                "   class HelloWorld\n" +

                "       var String str = \"hello!\"\n"+
                        "       var integer num = 5 \n" +
                        "       intArray(5) = new integer(1 num 3 \"hi\" 5)\n" +

                        "       method main requires () returns void\n" +
                        "           var String q = str\n" +
                        "           print \"hello\"\n" +

                        "        method temp requires () returns integer\n" +
                        "           return 23\n" +

                        "        method temp requires () returns integer\n" +
                        "           return 23\n";


        boolean success = false;

        Parser<?>[] parsers = new Parser<?>[]{
                //Parser's list.
                new ClassParser(),
                new MethodParser(),
                new VariableParser(),
                new PrintParser(),
                new ReturnParser(),
                new VariableArrayParser()
        };

        Class main = null;

        Block block = null;

        //parsing each line individually. acting like an interpreter.
        for(String line : code.split("\n")){
            line = line.trim(); // reducing empty spaces.
            success = false;
            Tokenizer tokenizer = new Tokenizer(line);
            for(Parser<?> parser : parsers){
                //checking for the specific parser for the mentioned source code line.
                if(parser.shouldParse(line)){

                    //if the parsing is for method block, then the super block has to be the initial class block of the current block.
                    if(parser instanceof MethodParser){block = block.getBlockTree().get(0);}

                    //actual parsing of blocks.
                    Block newBlock = parser.parse(block,tokenizer);

                    //creating block hierarchy of blocks according to acquired sequence of lines.
                    if(newBlock instanceof Class){
                        classes.add((Class) newBlock);
                        block = newBlock;
                    }
                    else if(newBlock instanceof Method){
                        //for any method, the block if added to the class(first block of a block tree.)
                        block.getBlockTree().get(0).addBlock(newBlock);
                        block = newBlock;
                    }
                    else if(newBlock instanceof VariableBlock) block.addBlock(newBlock);
                    else if (newBlock instanceof VariableArrayBlock) block.addBlock(newBlock);
                    else if(newBlock instanceof ReturnBlock) block.addBlock(newBlock);
                    else if(newBlock instanceof PrintBlock){
                        //for print block, add to the method phase of a block tree.
                        block.addBlock(newBlock);
                    }

                    //if the block is parses properly, success will finally be true.
                    success=true;
                    break;

                }
            }

            //if the success bool is still false, then it means all parsers have gone threw the line but none could parse it. failed to parse scenario.
            if (!success) throw new IllegalStateException("Could not parse : \"" + line + "\"");
        }


        for(Class c : classes){
            for(Block b : c.getSubBlocks()){
                if(b instanceof Method){
                    Method method = (Method) b;
                    if(method.getName().equals("main")&&method.getType().equals("void")&&method.getParams().length==0){main = c;}
                }

            }
        }

        if(main==null) throw new IllegalStateException("No main class found.");

        main.run();
    }
}
