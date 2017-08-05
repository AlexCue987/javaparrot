package org.parrot;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypedInstanceFactory {
    private static List<Class<?>> primitiveTypes = Arrays.asList(
            boolean.class,
            int.class,
            long.class,
            double.class,
            String.class
    );

    public static TypedInstance of(Object o) throws IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();
        List<TypedField> values = new ArrayList<>(fields.length);
        for(int i=0; i<fields.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            Object value = field.get(o);
            System.out.println(field);
            System.out.println(value);
            Class<?> type = field.getType();
            if(primitiveTypes.contains(type)) {
                values.add(new TypedField(type.getName(), value));
                continue;
            }
            TypedInstance typedInstance = (value == null) ? null : of(value);
            System.out.println("Non-primitive:" + type);
            values.add(new TypedField(type.getName(), typedInstance));
        }
        return new TypedInstance(o.getClass().getTypeName(), values);
    }
}
