package org.parrot.typed;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {

    private final Map<PersistingMethod, Deserializer> deserializerMap;

    public ObjectFactory() {
        deserializerMap = new HashMap<>();
        deserializerMap.put(PersistingMethod.VALUE, new DeserializerAsValue());
        deserializerMap.put(PersistingMethod.LIST, new DeserializerAsList(this));
        deserializerMap.put(PersistingMethod.MAP, new DeserializerAsMap(this));
        deserializerMap.put(PersistingMethod.FIELDS, new DeserializerAsFields(this));
    }

    public Object of(Map<String, Object> typedObjectFromJson){
        if(typedObjectFromJson == null || !typedObjectFromJson.containsKey("value")){
            return null;
        }
        String className = typedObjectFromJson.get("className").toString();
        PersistingMethod persistingMethod = getPersistingMethod(typedObjectFromJson);
        if(!deserializerMap.containsKey(persistingMethod)){
            throw new RuntimeException("not found: " + persistingMethod);
        }
        Deserializer deserializer = deserializerMap.get(persistingMethod);
        Object value = typedObjectFromJson.get("value");
        return deserializer.deserialize(className, value);
    }

    public PersistingMethod getPersistingMethod(Map<String, Object> typedObjectFromJson) {
        String persistingMethodStr = typedObjectFromJson.get("persistingMethod").toString();
        return PersistingMethod.valueOf(persistingMethodStr);
    }
}
