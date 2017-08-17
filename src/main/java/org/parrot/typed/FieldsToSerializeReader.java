package org.parrot.typed;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FieldsToSerializeReader {
    public static List<Field> getFieldsToSerialize(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        return Arrays.stream(fields).
                filter(field -> !field.isEnumConstant() &&
                        !field.isSynthetic() &&
                        !java.lang.reflect.Modifier.isStatic(field.getModifiers())).
                collect(Collectors.toList());
    }
}
