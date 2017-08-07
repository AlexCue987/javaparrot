package org.parrot;

import org.junit.Assert;
import org.junit.Test;

public class EmptyInstanceFactoryTests {
    @Test
    public void worksWithBuiltInType(){
        Object actual = new EmptyInstanceFactory().of("java.lang.String");
        Assert.assertTrue(actual instanceof String);
    }

    @Test
    public void worksWithCustomType(){
        Object actual = new EmptyInstanceFactory().of("org.parrot.Item");
        Assert.assertTrue(actual instanceof Item);
    }
}
