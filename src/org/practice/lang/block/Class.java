package org.practice.lang.block;

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

    }

    @Override
    public void run() {
        for(Block b : getSubBlocks()){
            if(b instanceof Method){
                Method method = (Method) b;
                if(method.getName().equals("main")&&method.getType().equals("void")&&method.getParams().length==0) method.run();
            }
            else b.run();
        }
    }

    public String getName() {
        return name;
    }
}
