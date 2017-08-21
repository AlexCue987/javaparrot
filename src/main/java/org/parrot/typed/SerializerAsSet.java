package org.parrot.typed;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SerializerAsSet extends SerializerAsCollection {

    public SerializerAsSet(TypedObjectFactory typedObjectFactory) {

        super(typedObjectFactory);
    }

    @Override
    public boolean canSerialize(Object object) {
        return (object instanceof Set);
    }

    @Override
    public List getList(Object object) {
        Set set = (Set) object;
        List list = new ArrayList(set);
        return list;
    }

    @Override
    public String getPersistingMethod() {
        return PersistingMethod.SET.toString();
    }

    @Override
    public TypedObject getTypedObject(Object object, List<TypedObject> typedObjects) {
        return new TypedObject(object.getClass().getTypeName(),
                getPersistingMethod(),
                typedObjects);
    }
}
