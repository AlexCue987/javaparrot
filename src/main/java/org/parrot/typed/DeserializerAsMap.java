package org.parrot.typed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeserializerAsMap implements Deserializer {
    private final ObjectFactory objectFactory;

    public DeserializerAsMap(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public Object deserialize(String className, Object mapValue) {
        List list = (List)mapValue;
        Map<Object, Object> untyped = new HashMap<>(list.size());
        for(Object object : list){
            Map entry = (Map)object;
            Object key = getObject(entry, EntryField.key);
            Object value = getObject(entry, EntryField.value);
            untyped.put(key, value);
        }
        return untyped;
    }

    private Object getObject(Object entry, EntryField entryField){
        Map entryAsMap = (Map)entry;
        @SuppressWarnings("unchecked")
        Map<String, Object> valueAsMap = (Map<String, Object>)(entryAsMap.get(entryField.toString()));
        return objectFactory.of(valueAsMap);
    }

    enum EntryField{key, value}
}
