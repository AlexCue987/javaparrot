package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;
import org.parrot.testobjects.Thing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypedObjectFactoryTests {
    TypedObjectFactory factory = new TypedObjectFactory();

    @Test
    public void worksWithValue(){
        Object object = 234L;
        TypedObject actual = factory.of(object);
        Assert.assertEquals("TypedObject(className=java.lang.Long, persistingMethod=VALUE, value=234)", actual.toString());
    }

    @Test
    public void worksWithList(){
        List<Object> list = new ArrayList<>();
        list.add(12);
        list.add(34L);
        TypedObject actual = factory.of(list);
        Assert.assertEquals("TypedObject(className=java.util.ArrayList, persistingMethod=LIST, value=[TypedObject(className=java.lang.Integer, persistingMethod=VALUE, value=12), " +
                "TypedObject(className=java.lang.Long, persistingMethod=VALUE, value=34)])", actual.toString());
    }

    @Test
    public void worksWithMap(){
        Map<Object, Object> map = new HashMap<>(2);
        map.put(123, "four");
        map.put("five", 6.7);
        TypedObject actual = factory.of(map);
        Assert.assertEquals("TypedObject(className=java.util.HashMap, persistingMethod=MAP, " +
                        "value=[TypedMapEntry(key=TypedObject(className=java.lang.String, persistingMethod=VALUE, value=five), value=TypedObject(className=java.lang.Double, persistingMethod=VALUE, value=6.7)), " +
                        "TypedMapEntry(key=TypedObject(className=java.lang.Integer, persistingMethod=VALUE, value=123), value=TypedObject(className=java.lang.String, persistingMethod=VALUE, value=four))])",
                actual.toString());
    }

    @Test
    public void worksWithFields(){
        Thing thing = new Thing("canoe", 14);
        TypedObject actual = factory.of(thing);
        Assert.assertEquals("TypedObject(className=org.parrot.testobjects.Thing, persistingMethod=FIELDS, value=[TypedField(fieldName=name, typedValue=TypedObject(className=java.lang.String, persistingMethod=VALUE, value=canoe)), TypedField(fieldName=size, typedValue=TypedObject(className=java.lang.Integer, persistingMethod=VALUE, value=14))])",
                actual.toString());
    }
}
