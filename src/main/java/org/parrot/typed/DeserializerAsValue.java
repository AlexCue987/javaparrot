package org.parrot.typed;

import java.util.Map;

public class DeserializerAsValue implements Deserializer {
    private final Map<String, ObjectFromStringFactory> factoryMap;

    public DeserializerAsValue() {
        factoryMap = ObjectFromStringFactoryMap.getFactoryMap();
    }

    @Override
    public Object deserialize(String className, Object object) {
        ObjectFromStringFactory factory = factoryMap.get(className);
        return factory.of(object.toString());
    }
}
