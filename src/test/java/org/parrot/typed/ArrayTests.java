package org.parrot.typed;

import org.junit.Test;

import java.lang.reflect.Array;

public class ArrayTests {
    @Test
    public void works() throws ClassNotFoundException {
        String[] array = new String[5];
        Class<? extends String[]> arrayClass = array.getClass();
        String className = arrayClass.getTypeName();
        System.out.println(className);
        Class<?> clazz = ArrayTests.class.getClassLoader().loadClass("java.lang.String");
        if(clazz.isArray()){
            System.out.println("array2");
        }
        String[] newArray = (String[]) Array.newInstance(clazz, 2);
        newArray[0] = "one";
        newArray[1] = "fox";
        System.out.println(newArray[0] + " " + newArray[1]);
//        Class<?> clazz2 = ArrayTests.class.getClassLoader().loadClass("java.lang.String[]");
        if(arrayClass.isArray()){
            System.out.println("array2");
        }
    }
}
