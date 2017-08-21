package org.parrot.typed;

import com.google.gson.Gson;

import java.util.Map;

public class DeserializerAsValue implements Deserializer {
    private final Map<String, ObjectFromStringFactory> factoryMap;

    public DeserializerAsValue() {
        factoryMap = ObjectFromStringFactoryMap.getFactoryMap();
    }

    @Override
    public Object deserialize(String className, Object object) {
        printValue(object);
        ObjectFromStringFactory factory = factoryMap.get(className);
        return factory.of(object.toString());
    }


    void printValue(Object value){
        Gson gson = new Gson();
        String json = gson.toJson(value);
        System.out.println(json);
    }
}
