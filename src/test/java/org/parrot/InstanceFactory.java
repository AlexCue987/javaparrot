package org.parrot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

public class InstanceFactory {
    private final EmptyInstanceFactory emptyInstanceFactory = new EmptyInstanceFactory();
    private final FieldsToHandleFactory fieldsToHandleFactory = new FieldsToHandleFactory();

    public Object of(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> map = gson.fromJson(json, type);
//        System.out.println(map);
        return ofMap(map);
    }

    public Object ofMap(Map<String, Object> map){
        String typeName = map.get("className").toString();
        Object instance = emptyInstanceFactory.of(typeName);
        List<Field> fieldsToPopulate = fieldsToHandleFactory.get(instance);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> fieldValues = (List<Map<String, Object>>)map.get("fields");
        for(int i=0; i<fieldsToPopulate.size(); i++){
            Field field = fieldsToPopulate.get(i);
            field.setAccessible(true);
            String fieldname = field.getName();
            Map<String, Object> fieldValue = getFieldValue(fieldValues, fieldname);
//            System.out.println(field);
//            String fieldType = fieldValue.get("type").toString();
//            Object value = fieldValue.get("value");
            Object originalValue = getOriginalValue(instance, field, fieldValue);
            setField(field, instance, originalValue);
            continue;
//            if(fieldValue.get("primitive").toString().equals("true")){
//                String valueStr = value.toString();
//                String name = fieldType.equals("java.lang.String") ? "STRING" : fieldType.toUpperCase();
//                PrimitiveType primitiveType = PrimitiveType.valueOf(name);
//                Object of = primitiveType.of(valueStr);
//                setField(field, instance, of);
//                continue;
//            }
//            Class<?> fieldClass = getaClass(fieldType);
//            if(List.class.isAssignableFrom(fieldClass)){
//                List list = ofList((List) value);
//                setField(field, instance, list);
//                continue;
//            }
//            if(Set.class.isAssignableFrom(fieldClass)){
//                Set set = ofSet((List)value);
//                setField(field, instance, set);
//                continue;
//            }
//            @SuppressWarnings("unchecked")
//            Map<String, Object> typedValueAsMap = (Map<String, Object>) value;
//            Object valueAsMap = typedValueAsMap==null ? null : ofMap(typedValueAsMap);
//            setField(field, instance, valueAsMap);
        }
        return instance;
    }

    public Object getOriginalValue(Object instance, Field field, Map<String, Object> fieldValue) {
        if(fieldValue == null){
            return null;
        }
        ValueType valueType = ValueType.valueOf(fieldValue.get("valueType").toString());
        return valueType.getValue(field, instance, fieldValue, this);
    }

    public Class<?> getaClass(String fieldType)  {
        try {
            return Class.forName(fieldType);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List ofList(List list){
        if(list == null){
            return null;
        }
        List ret = new ArrayList(list.size());
        for(int i=0; i<list.size(); i++){
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>)list.get(i);
            Object o = ofMap(map);
            ret.add(o);
        }
        return ret;
    }

    public Set ofSet(List list){
        if(list == null){
            return null;
        }
        Set ret = new HashSet(list.size());
        for(int i=0; i<list.size(); i++){
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>)list.get(i);
            Object o = ofMap(map);
            ret.add(o);
        }
        return ret;
    }

    public void setField(Field field, Object instance, Object object) {
        try {
            field.set(instance, object);
            return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> getFieldValue(List<Map<String, Object>> fieldValues,
                                             String fieldName) {
        Optional<Map<String, Object>> ret = fieldValues.stream().
                filter(t -> fieldName.equals(t.get("name"))).findFirst();
        return ret.orElse(null);
    }
}
