package org.parrot;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

public class FieldsToHandleFactoryTests {
    @Test
    public void get_handlesInteger(){
        List<Field> actual = new FieldsToHandleFactory().get(new Integer(123));
        Assert.assertEquals("[private final int java.lang.Integer.value]", actual.toString());
    }

    @Test
    public void get_handlesItem(){
        List<Field> actual = new FieldsToHandleFactory().get(new Item("my item"));
        Assert.assertEquals("[private final java.lang.String org.parrot.Item.itemName]", actual.toString());
    }
}
