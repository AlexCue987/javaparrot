package org.parrot.typed;

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
        throw new RuntimeException("Unsupported: " + o);
    }
}
