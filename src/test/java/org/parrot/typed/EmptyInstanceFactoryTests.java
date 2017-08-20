package org.parrot.typed;

import org.junit.Assert;
import org.junit.Test;

public class EmptyInstanceFactoryTests {

    @Test
    public void of_works(){
        String className = this.getClass().getTypeName();
        Object actual = EmptyInstanceFactory.create(className);
        Assert.assertTrue(actual instanceof EmptyInstanceFactoryTests);
    }
}
