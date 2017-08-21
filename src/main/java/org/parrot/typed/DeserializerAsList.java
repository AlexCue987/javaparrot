package org.parrot.typed;

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
}
