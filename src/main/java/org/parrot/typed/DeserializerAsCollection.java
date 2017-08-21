package org.parrot.typed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class DeserializerAsCollection  {
    protected final ObjectFactory objectFactory;

    public DeserializerAsCollection(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    protected List<Object> getItems(List value) {
        List list = value;
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
