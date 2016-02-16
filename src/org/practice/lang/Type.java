package org.practice.lang;

/**
 * Created by Anurag on 14-02-2016.
 *
 * Type interface for using the external class as a variable declaration type.
 */
public interface Type {

    static Type match(String str) {
        try {
            return BuiltInType.valueOf(str.toUpperCase());
        }

        catch (Exception e) {
            // TODO: Match str to a class.
            throw new IllegalStateException("illegal data type assignment : " + str);

        }
    }


}