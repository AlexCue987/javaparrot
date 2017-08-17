package org.parrot.typed;

import java.util.HashMap;
import java.util.Map;

public class SerializerAsValue implements Serializer {
    private final Map<String, ObjectFromStringFactory> factoryMap;

    public SerializerAsValue(Map<String, ObjectFromStringFactory> factoryMap) {
        this.factoryMap = factoryMap;
    }

    public SerializerAsValue() {
        this(ObjectFromStringFactoryMap.getFactoryMap());
    }

    @Override
    public boolean canSerialize(Object object) {
        String className = ObjectFromStringFactoryMap.getClassName(object);
        return factoryMap.containsKey(className);
    }

    @Override
    public TypedObject serialize(Object object) {
        return new TypedObject(object.getClass().getTypeName(),
                PersistingMethod.VALUE.toString(),
                object.toString());
    }
}
