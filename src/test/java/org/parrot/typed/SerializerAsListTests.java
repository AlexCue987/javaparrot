package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SerializerAsListTests {
    TypedObjectFactory factory = new TypedObjectFactory();
    SerializerAsList serializer = new SerializerAsList(factory);

    @Test
    public void serialize_works(){
        List<Object> list = getList();
        TypedObject actual = serializer.serialize(list);
        Assert.assertEquals("TypedObject(className=java.util.ArrayList, persistingMethod=LIST, " +
                        "value=[TypedObject(className=java.lang.Integer, persistingMethod=VALUE, value=123), " +
                        "TypedObject(className=java.lang.Long, persistingMethod=VALUE, value=234)])",
                actual.toString());
    }

    public List<Object> getList() {
        List<Object> list = new ArrayList<>(2);
        list.add(123);
        list.add(234L);
        return list;
    }
}
