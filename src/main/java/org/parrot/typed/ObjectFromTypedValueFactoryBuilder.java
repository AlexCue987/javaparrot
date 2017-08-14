package org.parrot.typed;

import java.util.HashMap;
import java.util.Map;

public class ObjectFromTypedValueFactoryBuilder {
    private final Map<String, ObjectFromStringFactory> factoryMap = getFactoryMap();

    public ObjectFromTypedValueFactoryBuilder withValueType(String className, ObjectFromStringFactory factory){
        factoryMap.put(className, factory);
        return this;
    }
    public ObjectFromTypedValueFactory create(){
        return new ObjectFromTypedValueFactory(factoryMap);
    }

    private Map<String, ObjectFromStringFactory> getFactoryMap(){
        Map<String, ObjectFromStringFactory> ret = new HashMap<>();
        ret.put("java.lang.Integer", Integer::valueOf);
        ret.put("java.lang.Long", Long::valueOf);
        return ret;
    }
}
