package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

public class PrimitiveTests {
    @Test
    public void isPrimitive_true(){
        Assert.assertTrue(Primitive.isPrimitive("int"));
    }

    @Test
    public void isPrimitive_false(){
        Assert.assertFalse(Primitive.isPrimitive("notAPrimitive"));
    }

    @Test
    public void worksForInt(){
        Assert.assertEquals(123, Primitive.INT.of("123"));
    }
}
