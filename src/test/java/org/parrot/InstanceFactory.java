package org.parrot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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
            String fieldType = fieldValue.get("type").toString();
            if(fieldValue.get("primitive").toString().equals("true")){
                try {
                    String value = fieldValue.get("value").toString();
                    String name = fieldType.equals("java.lang.String") ? "STRING" : fieldType.toUpperCase();
                    PrimitiveType primitiveType = PrimitiveType.valueOf(name);
                    Object of = primitiveType.of(value);
                    field.set(instance, of);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return instance;
    }

    public Map<String, Object> getFieldValue(List<Map<String, Object>> fieldValues,
                                             String fieldName) {
        Map<String, Object> ret = fieldValues.stream().
                filter(t -> fieldName.equals(t.get("name"))).findFirst().get();
        return ret;
    }
}
