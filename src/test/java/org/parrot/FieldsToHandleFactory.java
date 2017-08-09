package org.parrot;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FieldsToHandleFactory {
    public List<Field> get(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        return Arrays.stream(fields).
                filter(field -> !field.isEnumConstant() &&
                        !field.isSynthetic() &&
                        !java.lang.reflect.Modifier.isStatic(field.getModifiers())).
                collect(Collectors.toList());
    }
}
