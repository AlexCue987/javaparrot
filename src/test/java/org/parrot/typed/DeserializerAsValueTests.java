package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

public class DeserializerAsValueTests {
    final DeserializerAsValue deserializer = new DeserializerAsValue();

    @Test
    public void worksForLong(){
        String value = "2017";
        Object actual = deserializer.deserialize("java.lang.Long", value);
        Assert.assertEquals(2017L, actual);
    }

    @Test
    public void worksForBoolean(){
        String value = "false";
        Object actual = deserializer.deserialize("java.lang.Boolean", value);
        Assert.assertFalse((Boolean) actual);
    }
}
