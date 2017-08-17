package org.parrot.typed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SerializerAsMap implements Serializer {
    private final TypedObjectFactory typedObjectFactory;

    public SerializerAsMap(TypedObjectFactory typedObjectFactory) {
        this.typedObjectFactory = typedObjectFactory;
    }

    @Override
    public boolean canSerialize(Object object) {
        return (object instanceof Map);
    }

    @Override
    public TypedObject serialize(Object object) {
        Map map = (Map) object;
        List<TypedMapEntry> typedMapEntries = new ArrayList<>(map.size());
        for(Object key: map.keySet()){
            TypedObject typedKey = typedObjectFactory.of(key);
            TypedObject typedValue = typedObjectFactory.of(map.get(key));
            TypedMapEntry entry = new TypedMapEntry(typedKey, typedValue);
            typedMapEntries.add(entry);
        }
        return new TypedObject(object.getClass().getTypeName(),
                PersistingMethod.MAP.toString(),
                typedMapEntries);
    }
}
