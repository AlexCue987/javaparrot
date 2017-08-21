package org.parrot.typed;

import java.util.List;

public class DeserializerAsList extends DeserializerAsCollection implements Deserializer {
    public DeserializerAsList(ObjectFactory objectFactory) {
        super(objectFactory);
    }

    @Override
    public Object deserialize(String classNameIgnored, Object value) {
        List<Object> untyped = getItems((List) value);
        return untyped;
    }
}
