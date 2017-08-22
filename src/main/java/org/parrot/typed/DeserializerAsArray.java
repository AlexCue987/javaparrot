package org.parrot.typed;

import java.lang.reflect.Array;
import java.util.List;

public class DeserializerAsArray extends DeserializerAsCollection implements Deserializer {
    public DeserializerAsArray(ObjectFactory objectFactory) {
        super(objectFactory);
    }

    @Override
    public Object deserialize(String className, Object value) {
        List<Object> untyped = getItems((List) value);
        Class<?> clazz = getaClass(className);
        Object array = Array.newInstance(clazz, untyped.size());
        for(int i=0; i<untyped.size(); i++){
            Array.set(array, i, untyped.get(i));
        }
        return array;
    }

    private Class<?> getaClass(String className){
        try {
            return DeserializerAsArray.class.getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
