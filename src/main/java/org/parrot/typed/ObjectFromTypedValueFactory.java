package org.parrot.typed;

import java.util.Map;

public class ObjectFromTypedValueFactory {
    private final Map<String, ObjectFromStringFactory> factoryMap;

    public ObjectFromTypedValueFactory(Map<String, ObjectFromStringFactory> factoryMap) {
        this.factoryMap = factoryMap;
    }

    public Object of(TypedValue typedValue){
        ObjectFromStringFactory factory = factoryMap.get(typedValue.getClassName());
        return factory.of(typedValue.getValue());
    }
}
