package org.parrot.typed;

import java.util.Map;

public class ObjectFactory {
    private final ObjectFromTypedValueFactory factory = getFactory();

    public Object of(Map<String, Object> typedObjectFromJson){
        String persistingMethodStr = typedObjectFromJson.get("persistingMethod").toString();
        String className = typedObjectFromJson.get("className").toString();
        PersistingMethod persistingMethod = PersistingMethod.valueOf(persistingMethodStr);
        if(persistingMethod.equals(PersistingMethod.VALUE)){
            String value = typedObjectFromJson.get("value").toString();
            return factory.of(new TypedValue(className, value));
        }
        return null;
    }

    private static ObjectFromTypedValueFactory getFactory(){
        return new ObjectFromTypedValueFactoryBuilder().create();
    }
}
