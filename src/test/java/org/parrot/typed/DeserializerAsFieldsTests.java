package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class DeserializerAsFieldsTests {
    final ObjectFactory objectFactory = new ObjectFactory();
    final DeserializerAsFields deserializer = new DeserializerAsFields(objectFactory);

    @Test
    public void of_works(){
        String className = this.getClass().getTypeName();
        Object actual = deserializer.of(className);
        Assert.assertTrue(actual instanceof DeserializerAsFieldsTests);
    }

    @Test
    public void getTypedFieldsMap(){
        Map<String, Object> typedValueMap = new HashMap<>(2);
        typedValueMap.put("type", "java.lang.Long");
        typedValueMap.put("value", 42L);
        Map<String, Object> nameField = new HashMap<>(2);
        nameField.put("fieldName", "answer");
        nameField.put("typedValue", typedValueMap);
        List<Object> oneFieldAsList = Collections.singletonList(nameField);
        Map<String, Map<String, Object>> actual = deserializer.getTypedFieldsMap(oneFieldAsList);
        System.out.println(actual);
        Assert.assertEquals("{answer={type=java.lang.Long, value=42}}", actual.toString());
    }
}
