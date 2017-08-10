package org.parrot;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class IntegrationTests {
    @Test
    public void recreatesTypesTest() {
        TypesTest typesTest = new TypesTest(true, 1, 2L, 3.4, "five");
        TypedInstanceFactory typedInstanceFactory = new TypedInstanceFactory();
        TypedInstance typedInstance = typedInstanceFactory.of(typesTest);
        Gson gson = new Gson();
        String json = gson.toJson(typedInstance);
//        System.out.println(json);
        InstanceFactory instanceFactory = new InstanceFactory();
        Object actual = instanceFactory.of(json);
//        System.out.println(obj);
        Assert.assertEquals("TypesTest(active=true, anInt=1, aLong=2, aDouble=3.4, aString=five)", actual.toString());
        Assert.assertEquals(typesTest, actual);
    }

    @Test
    public void recreatesNestedTypesTest(){
        NestedTypesTest unnestedTypesTest = new NestedTypesTest(true, 1, 2L, 3.4, "five", null);
        NestedTypesTest nestedTypesTest = new NestedTypesTest(false, 2, 3L, 4.5, "six", unnestedTypesTest);
        TypedInstanceFactory typedInstanceFactory = new TypedInstanceFactory();
        TypedInstance typedInstance = typedInstanceFactory.of(nestedTypesTest);
        Gson gson = new Gson();
        String json = gson.toJson(typedInstance);
//        System.out.println(json);
        InstanceFactory instanceFactory = new InstanceFactory();
        Object actual = instanceFactory.of(json);
//        System.out.println(obj.toString());
//        System.out.println(obj.toString().replace(",", ",\n"));
        Assert.assertEquals("NestedTypesTest(active=false, anInt=2, aLong=3, aDouble=4.5, aString=six, nestedTypesTest=NestedTypesTest(active=true, anInt=1, aLong=2, aDouble=3.4, aString=five, nestedTypesTest=null))", actual.toString());
        Assert.assertEquals(nestedTypesTest, (NestedTypesTest)actual);
    }
}
