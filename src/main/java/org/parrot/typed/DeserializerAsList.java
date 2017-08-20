package org.parrot.typed;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeserializerAsList implements Deserializer {
    private final ObjectFactory objectFactory;

    public DeserializerAsList(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public Object deserialize(String classNameIgnored, Object value) {
        printValue(value);
        List list = (List)value;
        List<Object> untyped = new ArrayList<>(list.size());
        for(Object object : list){
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            Object originalObject = objectFactory.of(map);
            untyped.add(originalObject);
        }
        return untyped;
    }

    void printValue(Object value){
        Gson gson = new Gson();
        String json = gson.toJson(value);
        System.out.println(json);
    }
}
