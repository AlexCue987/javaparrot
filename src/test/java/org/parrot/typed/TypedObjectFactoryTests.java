package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

public class TypedObjectFactoryTests {
    @Test
    public void worksForInt(){
        final int anInt = 234;
        TypedObject typedObject = new TypedObjectFactory().of(anInt);
        Assert.assertEquals("TypedObject(className=java.lang.Integer, persistingMethod=VALUE, value=234)", typedObject.toString());
    }
}
