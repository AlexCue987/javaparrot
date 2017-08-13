package org.parrot;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class TypedInstanceFactory {
    private final static List<Class<?>> primitiveTypes = Arrays.asList(
            boolean.class,
            int.class,
            long.class,
            double.class,
//            Integer.class,
//            Long.class,
            String.class
    );

    public TypedInstance of(Object o){
        if(o == null){
            return null;
        }
        Class<?> objectType = o.getClass();
        if(primitiveTypes.contains(objectType)){
            return TypedInstance.getPrimitive(new TypedField(objectType.getTypeName(), "NotAField", ValueType.PRIMITIVE, o));
        }
        List<TypedField> values = new ArrayList<>();
        for(Field field : getFieldsToSave(o)){
            field.setAccessible(true);
            Object value = getObject(o, field);
            Class<?> type = field.getType();
            String fieldName = field.getName();
            String className = type.getTypeName();
            if(primitiveTypes.contains(type)) {
                values.add(new TypedField(className, fieldName, ValueType.PRIMITIVE, value.toString()));
                continue;
            }
            if(value instanceof List){
                values.add(ofList((List)value, fieldName));
                continue;
            }
            if(value instanceof Map){
                values.add(ofMap((Map)value, fieldName));
                continue;
            }
            if(value instanceof Set){
                values.add(ofSet((Set)value, fieldName));
                continue;
            }
            TypedInstance typedInstance = (value == null) ? null : of(value);
//            System.out.println("Non-primitive:" + type);
            values.add(new TypedField(className, fieldName, ValueType.INSTANCE, typedInstance));
        }
        return new TypedInstance(o.getClass().getTypeName(), ValueType.INSTANCE, values);
    }

    public List<Field> getFieldsToSave(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        return Arrays.stream(fields).
                filter(field -> !field.isEnumConstant() &&
                        !field.isSynthetic() &&
                        !java.lang.reflect.Modifier.isStatic(field.getModifiers())).
                collect(Collectors.toList());
    }

    public Object getObject(Object o, Field field) {
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public TypedField ofList(List list, String fieldName){
        if(list == null){
            return new TypedField(List.class.getTypeName(), fieldName, ValueType.LIST, null);
        }
        String typeName = list.getClass().getTypeName();
        List<TypedInstance> values = new ArrayList<>();
        for(Object o: list){
            TypedInstance typedInstance = of(o);
            values.add(typedInstance);
        }
        return new TypedField(typeName, fieldName, ValueType.LIST, values);
    }

    public TypedField ofSet(Set set, String fieldName){
        if(set == null){
            return new TypedField(Set.class.getTypeName(), fieldName, ValueType.SET, null);
        }
        String typeName = set.getClass().getTypeName();
        Set<TypedInstance> values = new HashSet<>();
        for(Object o: set){
            TypedInstance typedInstance = of(o);
            values.add(typedInstance);
        }
        return new TypedField(typeName, fieldName, ValueType.SET, values);
    }

    public TypedField ofMap(Map map, String fieldName){
        if(map == null){
            return new TypedField(Map.class.getTypeName(), fieldName, ValueType.MAP, null);
        }
        String typeName = map.getClass().getTypeName();
        List<TypedMapEntry> typedMapEntries = new ArrayList<>();
        for(Object key: map.keySet()){
            TypedInstance typedKey = of(key);
            TypedInstance typedValue = of(map.get(key));
            typedMapEntries.add(new TypedMapEntry(typedKey, typedValue));
        }
        return new TypedField(typeName, fieldName, ValueType.MAP, typedMapEntries);
    }
}
