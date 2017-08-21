package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SerializerAsSetTests {
    TypedObjectFactory factory = new TypedObjectFactory();
    SerializerAsSet serializer = new SerializerAsSet(factory);

    @Test
    public void serialize_works(){
        Set<String> list = getList();
        TypedObject actual = serializer.serialize(list);
        Assert.assertEquals("TypedObject(className=java.util.TreeSet, persistingMethod=SET, value=[TypedObject(className=java.lang.String, persistingMethod=VALUE, value=amber), " +
                        "TypedObject(className=java.lang.String, persistingMethod=VALUE, value=green)])",
                actual.toString());
    }

    public Set<String> getList() {
        Set<String> list = new TreeSet<String>();
        list.add("green");
        list.add("amber");
        return list;
    }
}
