package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SerializerAsMapTests {
    @Test
    public void works(){
        Map<Object, Object> map = new HashMap<>();
        map.put(123, 456L);
        map.put(12L, 34);
        TypedObjectFactory typedObjectFactory = new TypedObjectFactory();
        SerializerAsMap serializerAsMap = new SerializerAsMap(typedObjectFactory);
        TypedObject typedObject = serializerAsMap.serialize(map);
        String actual = typedObject.toString().replace(",", ",\n");
        System.out.println(actual);
        String expected = "TypedObject(className=java.util.HashMap,\n" +
                " persistingMethod=MAP,\n" +
                " value=[TypedMapEntry(key=TypedObject(className=java.lang.Integer,\n" +
                " persistingMethod=VALUE,\n" +
                " value=123),\n" +
                " value=TypedObject(className=java.lang.Long,\n" +
                " persistingMethod=VALUE,\n" +
                " value=456)),\n" +
                " TypedMapEntry(key=TypedObject(className=java.lang.Long,\n" +
                " persistingMethod=VALUE,\n" +
                " value=12),\n" +
                " value=TypedObject(className=java.lang.Integer,\n" +
                " persistingMethod=VALUE,\n" +
                " value=34))])";
        Assert.assertEquals(expected, actual);
    }
}
