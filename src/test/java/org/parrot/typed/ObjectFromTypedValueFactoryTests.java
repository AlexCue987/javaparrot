package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

public class ObjectFromTypedValueFactoryTests {
    private final ObjectFromTypedValueFactory factory = getFactory();

    @Test
    public void worksWithInteger(){
        Integer expected = 1234;
        TypedValue typedValue = TypedValue.of(expected);
        Object actual = factory.of(typedValue);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void worksWithLong(){
        Long expected = 1234L;
        TypedValue typedValue = TypedValue.of(expected);
        Object actual = factory.of(typedValue);
        Assert.assertEquals(expected, actual);
    }

    private static ObjectFromTypedValueFactory getFactory(){
        return new ObjectFromTypedValueFactoryBuilder().create();
    }
}
