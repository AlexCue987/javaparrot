package org.parrot;

import org.junit.Ignore;
import org.junit.Test;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;
import org.parrot.testobjects.TypesTest;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionTests {
    @Test
    public void works() throws IllegalAccessException {
        List<MethodCallParameter> parameters = Arrays.asList(
                new MethodCallParameter("type1", "value1"),
                new MethodCallParameter("type2", new BigDecimal("1.23")));
        MethodCall methodCall = new MethodCall(parameters, new Integer(234));
        TypedInstance typedInstance = getValues(methodCall);
        System.out.println(typedInstance.getClassName());
        typedInstance.getFields().forEach(System.out::println);
    }

    @Test
    public void works2() throws IllegalAccessException {
        TypesTest typesTest = new TypesTest(true, 1, 2L, 1.23, "some");
        TypedInstance typedInstance = getValues(typesTest);
        System.out.println(typedInstance.getClassName());
        typedInstance.getFields().forEach(System.out::println);
    }

    @Test
    public void t() throws ClassNotFoundException, IllegalAccessException {
        Objenesis objenesis = new ObjenesisStd();
        ClassLoader classLoader = ReflectionTests.class.getClassLoader();
        Class clazz = classLoader.loadClass("org.parrot.MethodCall");
        ObjectInstantiator thingyInstantiator = objenesis.getInstantiatorOf(clazz);
        Object o = thingyInstantiator.newInstance();
        System.out.println(o.getClass().toString());
        List<MethodCallParameter> parameters = new ArrayList<>();
        List<Object> values = Arrays.asList(parameters, "asdfa");
        setFields(o, values);
        System.out.println(o);
    }

    @Ignore
    @Test
    public void t1() throws ClassNotFoundException, IllegalAccessException {
        Objenesis objenesis = new ObjenesisStd();
        ClassLoader classLoader = ReflectionTests.class.getClassLoader();
        Class clazz = classLoader.loadClass("org.parrot.testobjects.TypesTest");
        ObjectInstantiator thingyInstantiator = objenesis.getInstantiatorOf(clazz);
        Object o = thingyInstantiator.newInstance();
        System.out.println(o.getClass().toString());
        List<MethodCallParameter> parameters = new ArrayList<>();
        List<Object> values = Arrays.asList(parameters, "asdfa");
        setFields(o, values);
        System.out.println(o);
    }

    void setFields(Object o, List<Object> values) throws IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();
        for(int i=0; i<fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            field.set(o, values.get(i));
        }
    }

    private TypedInstance getValues(Object o) throws IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();
        List<TypedField> values = new ArrayList<>(fields.length);
        for(int i=0; i<fields.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            Object value = field.get(o);
            System.out.println(field);
            System.out.println(value);
            //todo: support nested here
            values.add(new TypedField(field.getType().getName(), field.getName(), true, value));
        }
        return new TypedInstance(o.getClass().getTypeName(), values);
    }
}
