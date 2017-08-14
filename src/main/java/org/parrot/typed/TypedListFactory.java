package org.parrot.typed;

import java.util.ArrayList;
import java.util.List;

public class TypedListFactory {
    private final TypedObjectFactory typedObjectFactory;

    public TypedListFactory(TypedObjectFactory typedObjectFactory) {
        this.typedObjectFactory = typedObjectFactory;
    }

    public TypedObject get(List list){
        List<TypedObject> typedObjects = new ArrayList<>(list.size());
        for(Object item: list){
            TypedObject typedItem = typedObjectFactory.of(item);
            typedObjects.add(typedItem);
        }
        return new TypedObject(list.getClass().getTypeName(),
                PersistingMethod.LIST.toString(),
                typedObjects);
    }
}
