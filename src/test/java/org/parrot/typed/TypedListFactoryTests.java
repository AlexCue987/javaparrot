package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TypedListFactoryTests {
    @Test
    public void works(){
        List<Object> items = Arrays.asList(new Integer(12), new Long(34L));
        TypedObjectFactory typedObjectFactory = new TypedObjectFactory();
        TypedListFactory typedListFactory = new TypedListFactory(typedObjectFactory);
        TypedObject typedObject = typedListFactory.get(items);
        String actual = typedObject.toString().replace(",", ",\n");
//        System.out.println(actual);
        Assert.assertEquals("TypedObject(className=java.util.Arrays$ArrayList,\n" +
                " persistingMethod=LIST,\n" +
                " value=[TypedObject(className=java.lang.Integer,\n" +
                " persistingMethod=VALUE,\n" +
                " value=12),\n" +
                " TypedObject(className=java.lang.Long,\n" +
                " persistingMethod=VALUE,\n" +
                " value=34)])", actual);
    }
}
