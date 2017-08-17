package org.parrot.typed;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeserializerAsFields implements Deserializer {
    private final ObjectFactory objectFactory;

    public DeserializerAsFields(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public Object deserialize(String className, Object value) {
        Object ret = of(className);
        List<Field> fieldsToPopulate = FieldsToSerializeReader.getFieldsToSerialize(value);
        for(int i=0; i<fieldsToPopulate.size(); i++) {
            Field field = fieldsToPopulate.get(i);
            field.setAccessible(true);
            String fieldname = field.getName();
        }
        List list = (List)value;
        List<Object> untyped = new ArrayList<>(list.size());
        for(Object object : list){
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) object;
            Object originalObject = objectFactory.of(map);
            untyped.add(originalObject);
        }
        return ret;
    }

    public Object of(String typeName){
        try {
            Objenesis objenesis = new ObjenesisStd();
            ClassLoader classLoader = DeserializerAsFields.class.getClassLoader();
            Class clazz = classLoader.loadClass(typeName);
            ObjectInstantiator thingyInstantiator = objenesis.getInstantiatorOf(clazz);
            return thingyInstantiator.newInstance();
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
