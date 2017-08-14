package org.parrot.typed;

import java.util.ArrayList;
import java.util.List;
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
        if(persistingMethod.equals(PersistingMethod.LIST)){
            List list = (List)typedObjectFromJson.get("value");
            List<Object> untyped = new ArrayList<>(list.size());
            for(Object object : list){
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) object;
                Object originalObject = of(map);
                untyped.add(originalObject);
            }
            return untyped;
        }
        return null;
    }

    private static ObjectFromTypedValueFactory getFactory(){
        return new ObjectFromTypedValueFactoryBuilder().create();
    }
}
