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
        System.out.println(map);
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
            Map<String, Object> fieldValue = fieldValues.get(i);
            System.out.println(field);
            String fieldType = fieldValue.get("type").toString();
            if(fieldValue.get("primitive").toString().equals("true")){
                try {
                    field.set(instance, fieldValue.get("value"));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return instance;
    }
}
