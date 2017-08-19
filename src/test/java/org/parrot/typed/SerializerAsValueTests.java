package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

public class SerializerAsValueTests {
    SerializerAsValue serializer = new SerializerAsValue();

    @Test
    public void canSerialize_null(){
        Assert.assertTrue(serializer.canSerialize(null));
    }

    @Test
    public void serialize_null(){
        Assert.assertTrue(serializer.serialize(null) == null);
    }
}
