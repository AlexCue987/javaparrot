package org.parrot.typed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFactory {
    private final ObjectFromTypedValueFactory factory = getFactory();

    private final Map<PersistingMethod, Deserializer> deserializerMap;

    public ObjectFactory() {
        deserializerMap = new HashMap<>();
        deserializerMap.put(PersistingMethod.VALUE, new DeserializerAsValue());
        deserializerMap.put(PersistingMethod.LIST, new DeserializerAsList(this));
    }

    public Object of(Map<String, Object> typedObjectFromJson){
        String persistingMethodStr = typedObjectFromJson.get("persistingMethod").toString();
        String className = typedObjectFromJson.get("className").toString();
        PersistingMethod persistingMethod = PersistingMethod.valueOf(persistingMethodStr);
        if(!deserializerMap.containsKey(persistingMethod)){
            throw new RuntimeException("not found: " + persistingMethod);
        }
        Deserializer deserializer = deserializerMap.get(persistingMethod);
        Object value = typedObjectFromJson.get("value");
        return deserializer.deserialize(className, value);
    }

    private static ObjectFromTypedValueFactory getFactory(){
        return new ObjectFromTypedValueFactoryBuilder().create();
    }
}
