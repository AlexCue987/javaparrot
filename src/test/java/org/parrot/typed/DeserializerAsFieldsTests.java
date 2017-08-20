package org.parrot.typed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.parrot.testobjects.Thing;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

public class DeserializerAsFieldsTests {
    final ObjectFactory objectFactory = new ObjectFactory();
    final DeserializerAsFields deserializer = new DeserializerAsFields(objectFactory);

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

    @Test
    public void setField(){
        String className = Thing.class.getTypeName();
        Thing thing = (Thing)EmptyInstanceFactory.create(className);
        Assert.assertTrue(thing.getName() == null);
        List<Field> fieldsToPopulate = FieldsToSerializeReader.getFieldsToSerialize(thing);
        String kayak = "kayak";
        deserializer.setField(thing, fieldsToPopulate.get(0), kayak);
        Assert.assertTrue(thing.getName().equals(kayak));
    }

    @Test
    public void deserialize_works(){
        String json = "[{\"fieldName\":\"name\",\"typedValue\":{\"className\":\"java.lang.String\",\"persistingMethod\":\"VALUE\",\"value\":\"shoe\"}}," +
                "{\"fieldName\":\"size\",\"typedValue\":{\"className\":\"java.lang.Integer\",\"persistingMethod\":\"VALUE\",\"value\":\"12\"}}]";
        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {}.getType();
        Object mapFromJson = gson.fromJson(json, type);
        Object actual = deserializer.deserialize(Thing.class.getTypeName(), mapFromJson);
        Thing expected = new Thing("shoe", 12);
        Assert.assertEquals(expected, actual);
    }
}
