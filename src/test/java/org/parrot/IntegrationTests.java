package org.parrot;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.parrot.testobjects.Thing;
import org.parrot.testobjects.TypesTest;
import org.parrot.testobjects.WithListOfThings;

import java.util.ArrayList;
import java.util.List;

public class IntegrationTests {
    TypedInstanceFactory typedInstanceFactory = new TypedInstanceFactory();
    Gson gson = new Gson();
    InstanceFactory instanceFactory = new InstanceFactory();

    @Test
    public void recreatesTypesTest() {
        TypesTest typesTest = new TypesTest(true, 1, 2L, 3.4, "five");
        TypedInstance typedInstance = typedInstanceFactory.of(typesTest);
        String json = gson.toJson(typedInstance);
//        System.out.println(json);
        Object actual = instanceFactory.of(json);
//        System.out.println(obj);
        Assert.assertEquals("TypesTest(active=true, anInt=1, aLong=2, aDouble=3.4, aString=five)", actual.toString());
        Assert.assertEquals(typesTest, actual);
    }

    @Test
    public void recreatesNestedTypesTest(){
        NestedTypesTest unnestedTypesTest = new NestedTypesTest(true, 1, 2L, 3.4, "five", null);
        NestedTypesTest nestedTypesTest = new NestedTypesTest(false, 2, 3L, 4.5, "six", unnestedTypesTest);
        TypedInstance typedInstance = typedInstanceFactory.of(nestedTypesTest);
        String json = gson.toJson(typedInstance);
//        System.out.println(json);
        Object actual = instanceFactory.of(json);
//        System.out.println(obj.toString());
//        System.out.println(obj.toString().replace(",", ",\n"));
        Assert.assertEquals("NestedTypesTest(active=false, anInt=2, aLong=3, aDouble=4.5, aString=six, nestedTypesTest=NestedTypesTest(active=true, anInt=1, aLong=2, aDouble=3.4, aString=five, nestedTypesTest=null))", actual.toString());
        Assert.assertEquals(nestedTypesTest, (NestedTypesTest)actual);
    }

    @Test
    public void recreatesWithListOfThings_nullList(){
        WithListOfThings withListOfThings = new WithListOfThings("with null list", 0, null);
        TypedInstance typedInstance = typedInstanceFactory.of(withListOfThings);
        String json = gson.toJson(typedInstance);
        Object actual = instanceFactory.of(json);
//        System.out.println(actual);
        Assert.assertEquals(withListOfThings, actual);
    }

    @Test
    public void recreatesWithListOfThings_emptyList(){
        List<Thing> things = new ArrayList<>();
        WithListOfThings withListOfThings = new WithListOfThings("with null list", 0, things);
        TypedInstance typedInstance = typedInstanceFactory.of(withListOfThings);
        String json = gson.toJson(typedInstance);
        Object actual = instanceFactory.of(json);
//        System.out.println(actual);
        Assert.assertEquals(withListOfThings, actual);
    }
}
