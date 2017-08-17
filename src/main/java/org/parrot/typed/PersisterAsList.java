package org.parrot.typed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersisterAsList implements Persister {
    private final TypedObjectFactory typedObjectFactory;
    private final ObjectFactory objectFactory;

    public PersisterAsList(TypedObjectFactory typedObjectFactory, ObjectFactory objectFactory) {
        this.typedObjectFactory = typedObjectFactory;
        this.objectFactory = objectFactory;
    }

    @Override
    public boolean canPersist(Object object) {
        return (object instanceof List);
    }

    @Override
    public TypedObject getTyped(Object object) {
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

    @Override
    public Object getUntyped(TypedObject typedObject) {
        List list = (List)(typedObject.getValue());
        List<Object> untyped = new ArrayList<>(list.size());
        for(Object object : list){
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            Object originalObject = objectFactory.of(map);
            untyped.add(originalObject);
        }
        return untyped;
    }
}
