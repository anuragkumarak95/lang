package org.practice.lang.block;

import org.practice.lang.Parameter;
import org.practice.lang.Type;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Represents a class block.
 */
public class Class extends Block implements Type{
    private final String TAG = "ClassBlock/ : ";
    private String name;



    //constructor: as a class doesn't have any parent node, the superClass for this is null.
    public Class(String name) {
        super(null);
        this.name = name;
        init();

    }

    @Override
    public void init() {
        for(Block b : getSubBlocks()){
            b.init();
        }
    }

    @Override
    public void addBlock(Block block) {
        //override the add block as to check the uniqueness of every method block under a class.
        //TODO : i.e., a method name and parameters combination has to be unique. -->(not working in the case of args less equivalent functions.)<--
        if (block.getClass() == Method.class) {
            for (Block b : getSubBlocks()) {
                if (b instanceof Method) {
                    if (((Method) b).getName().equals(((Method) block).getName())) {

                        for (Parameter p : ((Method) b).getParams()) {
                            for (Parameter pNew : ((Method) block).getParams()) {
                                if (!p.getType().toString().equals(pNew.getType().toString())) break;
                            }
                            throw new IllegalStateException("Another method of same name is initialised. : " +
                                    ((Method) b).getName() + " returns " + ((Method) b).getType());
                        }
                    }

                }
            }
        }

        super.addBlock(block);

    }

    /**
     * Method used to run the main method of the input source block of this specific class instance.
     */
    public void run() {
        for(Block b : getSubBlocks()){
            if(b instanceof Method){
                Method method = (Method) b;
                if(method.getName().equals("main")&&method.getType().equals("void")&&method.getParams().length==0) method.run();
            }
        }
    }

    public String getName() {
        return name;
    }

}
