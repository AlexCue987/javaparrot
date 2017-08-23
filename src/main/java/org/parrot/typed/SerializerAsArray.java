package org.parrot.typed;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SerializerAsArray extends SerializerAsCollection {

    public SerializerAsArray(TypedObjectFactory typedObjectFactory) {

        super(typedObjectFactory);
    }

    @Override
    public boolean canSerialize(Object object) {
        return (object.getClass().isArray());
    }

    @Override
    public List getList(Object object) {
        int length = Array.getLength(object);
        List<Object> list = new ArrayList<>(length);
        for (int i = 0; i < length; i ++) {
            Object arrayElement = Array.get(object, i);
            list.add(arrayElement);
        }
        return list;
    }

    @Override
    public String getPersistingMethod() {
        return PersistingMethod.ARRAY.toString();
    }

    @Override
    public TypedObject getTypedObject(Object object, List<TypedObject> typedObjects) {
        return new TypedObject(getArrayElementTypeName(object),
                getPersistingMethod(),
                typedObjects);
    }

    public String getArrayElementTypeName(Object object) {
        String arrayTypeName = object.getClass().getComponentType().getTypeName();
        return arrayTypeName.replace("[]", "");
    }
}
