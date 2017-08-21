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
        Assert.assertEquals("afd", actual.toString());
    }

    public List<Object> getList() {
        List<Object> list = new ArrayList<>(2);
        list.add(123);
        list.add(234L);
        return list;
    }

}
