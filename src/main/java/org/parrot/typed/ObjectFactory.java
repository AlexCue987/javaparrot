package org.parrot.typed;

import java.util.Map;

public class ObjectFactory {
    public Object of(Map<String, Object> typedObjectFromJson){
        String persistingMethodStr = typedObjectFromJson.get("persistingMethod").toString();
        String className = typedObjectFromJson.get("className").toString();
        PersistingMethod persistingMethod = PersistingMethod.valueOf(persistingMethodStr);
        if(persistingMethod.equals(PersistingMethod.VALUE)){
            return Primitive.valueOf(className, typedObjectFromJson.get("value").toString());
        }
        return null;
    }
}
