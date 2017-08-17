package org.parrot.typed;

import java.util.ArrayList;
import java.util.List;

public class SerializerAsList implements Serializer {
    private final TypedObjectFactory typedObjectFactory;

    public SerializerAsList(TypedObjectFactory typedObjectFactory) {
        this.typedObjectFactory = typedObjectFactory;
    }

    @Override
    public boolean canSerialize(Object object) {
        return (object instanceof List);
    }

    @Override
    public TypedObject serialize(Object object) {
        List list = (List) object;
        List<TypedObject> typedObjects = new ArrayList<>(list.size());
        for(Object item: list){
            TypedObject typedItem = typedObjectFactory.of(item);
            typedObjects.add(typedItem);
        }
        return new TypedObject(object.getClass().getTypeName(),
                PersistingMethod.LIST.toString(),
                typedObjects);
    }
}
