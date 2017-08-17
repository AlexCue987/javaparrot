package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TypedObjectFactoryTests {
    @Test
    public void worksForInt(){
        final int anInt = 234;
        TypedObject typedObject = new TypedObjectFactory().of(anInt);
        Assert.assertEquals("TypedObject(className=java.lang.Integer, persistingMethod=VALUE, value=234)", typedObject.toString());
    }

    @Test
    public void worksForList(){
        List<Object> list = new ArrayList<>(2);
        list.add(123);
        list.add(234L);
        TypedObject typedObject = new TypedObjectFactory().of(list);
        String expected = "TypedObject(className=java.util.ArrayList, persistingMethod=LIST, value=[TypedObject(className=java.lang.Integer, persistingMethod=VALUE, value=123), TypedObject(className=java.lang.Long, persistingMethod=VALUE, value=234)])";
        Assert.assertEquals(expected, typedObject.toString());
    }
}
