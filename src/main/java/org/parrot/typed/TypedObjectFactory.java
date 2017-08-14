package org.parrot.typed;

import java.util.List;

public class TypedObjectFactory {
    private final ObjectFromTypedValueFactory objectFromTypedValueFactory;

    public TypedObjectFactory() {
        objectFromTypedValueFactory = new ObjectFromTypedValueFactoryBuilder().create();
    }

    public TypedObject of(Object o){
        String className = o.getClass().getTypeName();
        if(objectFromTypedValueFactory.isValueType(className)){
            return TypedObject.getPrimitive(o);
        }
        if(o instanceof List){
            TypedListFactory typedListFactory = new TypedListFactory(this);
            return typedListFactory.get((List) o);
        }
        throw new RuntimeException("Unsupported: " + o);
    }
}
