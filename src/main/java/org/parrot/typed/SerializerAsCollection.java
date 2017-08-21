package org.parrot.typed;

import java.util.ArrayList;
import java.util.List;

public abstract class SerializerAsCollection implements Serializer {
    private final TypedObjectFactory typedObjectFactory;

    public SerializerAsCollection(TypedObjectFactory typedObjectFactory) {
        this.typedObjectFactory = typedObjectFactory;
    }

    @Override
    public abstract boolean canSerialize(Object object);

    @Override
    public TypedObject serialize(Object object) {
        List list = getList(object);
        List<TypedObject> typedObjects = new ArrayList<>(list.size());
        for(Object item: list){
            TypedObject typedItem = typedObjectFactory.of(item);
            typedObjects.add(typedItem);
        }
        return getTypedObject(object, typedObjects);
    }

    public abstract TypedObject getTypedObject(Object object, List<TypedObject> typedObjects);

    public abstract List getList(Object object);

    public abstract String getPersistingMethod();
}
