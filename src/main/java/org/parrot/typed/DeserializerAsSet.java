package org.parrot.typed;

import java.util.HashSet;
import java.util.List;

public class DeserializerAsSet extends DeserializerAsCollection implements Deserializer {
    public DeserializerAsSet(ObjectFactory objectFactory) {
        super(objectFactory);
    }

    @Override
    public Object deserialize(String classNameIgnored, Object value) {
        List<Object> untyped = getItems((List) value);
        return new HashSet<>(untyped);
    }
}
