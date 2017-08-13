package org.parrot.typed;

public class TypedObjectFactory {
    public TypedObject of(Object o){
        String className = o.getClass().getTypeName();
        if(Primitive.isPrimitive(className)){
            return TypedObject.getPrimitive(o);
        }
        throw new RuntimeException("Unsupported: " + o);
    }
}
