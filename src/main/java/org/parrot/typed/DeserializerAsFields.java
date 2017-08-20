package org.parrot.typed;

import com.google.gson.Gson;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeserializerAsFields implements Deserializer {
    private final ObjectFactory objectFactory;

    public DeserializerAsFields(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public Object deserialize(String className, Object value) {
        @SuppressWarnings("unchecked")
        List<Object> typedFields = (List<Object>)value;
        Map<String, Map<String, Object>> typedFieldsMap = getTypedFieldsMap(typedFields);
        Object ret = of(className);
        List<Field> fieldsToPopulate = FieldsToSerializeReader.getFieldsToSerialize(ret);
        for(int i=0; i<fieldsToPopulate.size(); i++) {
            Field field = fieldsToPopulate.get(i);
            String fieldname = field.getName();
            Map<String, Object> typedFieldMap = typedFieldsMap.get(fieldname);
            Object untypedObject = objectFactory.of(typedFieldMap);
            setField(ret, field, untypedObject);
        }
        return ret;
    }

    public void setField(Object ret, Field field, Object untypedObject) {
        try {
            field.setAccessible(true);
            field.set(ret, untypedObject);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    Map<String, Map<String, Object>> getTypedFieldsMap(List<Object> typedFields){
        Map<String, Map<String, Object>> ret = new HashMap<>();
        for(Object typedField : typedFields){
            Map<String, Object> typedFieldMap = (Map<String, Object>)typedField;
            String fieldName = typedFieldMap.get("fieldName").toString();
            Object typedValue = typedFieldMap.get("typedValue");
            @SuppressWarnings("unchecked")
            Map<String, Object> typedValueMap = (Map<String, Object>) typedValue;
            ret.put(fieldName, typedValueMap);
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
