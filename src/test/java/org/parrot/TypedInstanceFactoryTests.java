package org.parrot;

import org.junit.Test;

public class TypedInstanceFactoryTests {
    @Test
    public void works() throws IllegalAccessException {
        TypedInstance instance = TypedInstanceFactory.of(new TypesTest(false, 1, 2L, 3.4, "five"));
        System.out.println(instance);
    }

    @Test
    public void nested() throws IllegalAccessException {
        TypesNestedTest t1 = new TypesNestedTest(false, 1, 2L, 3.4, "five", null);
        TypesNestedTest t2 = new TypesNestedTest(true, 2, 3L, 4.5, "six", t1);
        TypedInstance instance = TypedInstanceFactory.of(t2);
        String s = instance.toString().replace(",", "\n,");
        System.out.println(s);
    }
}
