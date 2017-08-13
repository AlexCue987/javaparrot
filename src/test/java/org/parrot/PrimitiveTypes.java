package org.parrot;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrimitiveTypes {
    private final static List<Class<?>> primitiveTypes = Arrays.asList(
            boolean.class,
            int.class,
            long.class,
            double.class,
//            Integer.class,
//            Long.class,
            String.class
    );

    private final static List<String> primitiveTypeNames = getPrimitiveTypeNamesFromTypes();

    private  static List<String> getPrimitiveTypeNamesFromTypes(){
        return primitiveTypes.stream().
                map(t -> t.getClass().getTypeName()).
                collect(Collectors.toList());
    }

    public static List<Class<?>> getPrimitiveTypes(){
        return primitiveTypes;
    }

    public static List<String> getPrimitiveTypeNames(){
        return primitiveTypeNames;
    }
}
