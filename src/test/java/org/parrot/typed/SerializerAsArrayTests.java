package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;
import org.parrot.testobjects.Thing;

import java.util.ArrayList;
import java.util.List;

public class SerializerAsArrayTests {
    TypedObjectFactory typedObjectFactory = new TypedObjectFactory();
    SerializerAsArray serializer = new SerializerAsArray(typedObjectFactory);

    @Test
    public void works(){
        Object[] objects = new Object[]{1, 2L, "three"};
        TypedObject actual = serializer.serialize(objects);
        Assert.assertEquals("TypedObject(className=java.lang.Object, persistingMethod=ARRAY, value=[" +
                        "TypedObject(className=java.lang.Integer, persistingMethod=VALUE, value=1), " +
                        "TypedObject(className=java.lang.Long, persistingMethod=VALUE, value=2), " +
                        "TypedObject(className=java.lang.String, persistingMethod=VALUE, value=three)])",
                actual.toString());
    }

    @Test
    public void canSerialize_trueForArray(){
        Object array = new Thing[1];
        Assert.assertTrue(serializer.canSerialize(array));
    }

    @Test
    public void canSerialize_falseForNotArray(){
        Object notArray = new ArrayList<Thing>();
        Assert.assertFalse(serializer.canSerialize(notArray));
    }

    @Test
    public void getArrayElementTypeName_returnsPrimitive(){
        Object object = new boolean[1];
        Assert.assertEquals("boolean", serializer.getArrayElementTypeName(object));
    }

    @Test
    public void getArrayElementTypeName_returnsClassName(){
        Object object = new String[1];
        Assert.assertEquals("java.lang.String", serializer.getArrayElementTypeName(object));
    }

    @Test
    public void getList(){
        Object[] objects = new Object[]{1, 2L, "three"};
        List actual = serializer.getList(objects);
        List<Object> expected = new ArrayList<>(3);
        expected.add(1);
        expected.add(2L);
        expected.add("three");
        Assert.assertEquals(expected, actual);
    }
}
