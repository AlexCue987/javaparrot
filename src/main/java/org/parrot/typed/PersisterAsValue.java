package org.parrot.typed;

import java.util.Map;

public class PersisterAsValue implements Persister {
    private final Map<String, ObjectFromStringFactory> factoryMap;

    public PersisterAsValue(Map<String, ObjectFromStringFactory> factoryMap) {
        this.factoryMap = factoryMap;
    }

    @Override
    public boolean canPersist(Object object) {
        String className = getClassName(object);
        return factoryMap.containsKey(className);
    }

    public String getClassName(Object object) {
        return object.getClass().getTypeName();
    }

    @Override
    public TypedObject getTyped(Object object) {
        return new TypedObject(object.getClass().getTypeName(),
                PersistingMethod.VALUE.toString(),
                object.toString());
    }

    @Override
    public Object getUntyped(TypedObject typedObject) {
        String className = typedObject.getClassName();
        ObjectFromStringFactory factory = factoryMap.get(className);
        return factory.of(typedObject.getValue().toString());
    }
}
