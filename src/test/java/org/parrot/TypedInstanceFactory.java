package org.parrot;

import java.lang.reflect.Field;
import java.util.*;

public class TypedInstanceFactory {
    private final static List<Class<?>> primitiveTypes = Arrays.asList(
            boolean.class,
            int.class,
            long.class,
            double.class,
            String.class
    );

    public TypedInstance of(Object o){
        if(o == null){
            return null;
        }
        Class<?> objectType = o.getClass();
        if(primitiveTypes.contains(objectType)){
            return new TypedInstance(new TypedField(objectType.toString(), o));
        }
        Field[] fields = o.getClass().getDeclaredFields();
        List<TypedField> values = new ArrayList<>(fields.length);
        for(int i=0; i<fields.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            Object value = getObject(o, field);
            Class<?> type = field.getType();
            if(primitiveTypes.contains(type)) {
                values.add(new TypedField(type.getName(), value));
                continue;
            }
            if(value instanceof List){
                values.add(ofList((List)value));
                continue;
            }
            if(value instanceof Map){
                values.add(ofMap((Map)value));
                continue;
            }
            TypedInstance typedInstance = (value == null) ? null : of(value);
            System.out.println("Non-primitive:" + type);
            values.add(new TypedField(type.getName(), typedInstance));
        }
        return new TypedInstance(o.getClass().getTypeName(), values);
    }

    public Object getObject(Object o, Field field) {
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public TypedField ofList(List list){
        String typeName = list.getClass().getTypeName();
        if(list == null){
            return new TypedField(typeName, null);
        }
        List<TypedInstance> values = new ArrayList<>();
        for(Object o: list){
            TypedInstance typedInstance = of(o);
            values.add(typedInstance);
        }
        return new TypedField(typeName, values);
    }

    public TypedField ofMap(Map map){
        if(map == null){
            return new TypedField(Map.class.getName(), null);
        }
        String typeName = map.getClass().getTypeName();
        List<TypedMapEntry> typedMapEntries = new ArrayList<>();
        for(Object key: map.keySet()){
            TypedInstance typedKey = of(key);
            TypedInstance typedValue = of(map.get(key));
            typedMapEntries.add(new TypedMapEntry(typedKey, typedValue));
        }
        return new TypedField(typeName, typedMapEntries);
    }
}
