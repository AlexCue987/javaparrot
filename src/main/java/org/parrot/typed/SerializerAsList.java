package org.parrot.typed;

import java.util.List;

public class SerializerAsList extends SerializerAsCollection {

    public SerializerAsList(TypedObjectFactory typedObjectFactory) {

        super(typedObjectFactory);
    }

    @Override
    public boolean canSerialize(Object object) {
        return (object instanceof List);
    }

    @Override
    public List getList(Object object) {
        return (List)object;
    }

    @Override
    public String getPersistingMethod() {
        return PersistingMethod.LIST.toString();
    }

    @Override
    public TypedObject getTypedObject(Object object, List<TypedObject> typedObjects) {
        return new TypedObject(object.getClass().getTypeName(),
                getPersistingMethod(),
                typedObjects);
    }
}
