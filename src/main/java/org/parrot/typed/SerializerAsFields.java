package org.parrot.typed;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SerializerAsFields implements Serializer {
    private final TypedObjectFactory typedObjectFactory;

    public SerializerAsFields(TypedObjectFactory typedObjectFactory) {
        this.typedObjectFactory = typedObjectFactory;
    }

    @Override
    public boolean canSerialize(Object object) {
        return (object instanceof List);
    }

    @Override
    public TypedObject serialize(Object object) {
        List<Field> fields = FieldsToSerializeReader.getFieldsToSerialize(object);
        List<TypedObject> typedObjects = new ArrayList<>(fields.size());
        for(Field field : fields){
            field.setAccessible(true);
            Object value = getObject(object, field);
            TypedObject typedObject = typedObjectFactory.of(value);
            typedObjects.add(typedObject);
        }
        return new TypedObject(object.getClass().getTypeName(),
                PersistingMethod.FIELDS.toString(),
                typedObjects);
    }

    private static Object getObject(Object o, Field field) {
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
