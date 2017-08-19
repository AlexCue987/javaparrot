package org.parrot.typed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;
import org.parrot.testobjects.Thing;
import org.parrot.testobjects.TypesTest;
import org.parrot.testobjects.WithAllPrimitiveTypes;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegrationTests {
    TypedObjectFactory typedObjectFactory = new TypedObjectFactory();
    Gson gson = new Gson();
    ObjectFactory objectFactory = new ObjectFactory();

    @Test
    public void recreatesInteger(){
        int anInt = 123;
        assertRecreates(anInt);
    }

    @Test
    public void recreatesLong(){
        long aLong = 234L;
        assertRecreates(aLong);
    }

    @Test
    public void recreatesList(){
        List<Object> items = Arrays.asList(new Integer(12), new Long(34L));
        assertRecreates(items);
    }

    @Test
    public void recreatesMap(){
        Map<Object, Object> map = new HashMap<>();
        map.put(123, 456L);
        map.put(12L, 34);
        assertRecreates(map);
    }

    @Test
    public void recreatesAsFields(){
        Thing thing = new Thing("box", 4);
        assertRecreates(thing);
    }

    @Test
    public void recreatesTypesTest(){
        Object typesTest = new TypesTest(true, 1, 2L, 3.4, "five");
        assertRecreates(typesTest);
    }

    public void assertRecreates(Object typesTest) {
        TypedObject typedThing = typedObjectFactory.of(typesTest);
        Map<String, Object> map = toJsonAndBack(typedThing);
        Object actual = objectFactory.of(map);
        Assert.assertEquals(typesTest, actual);
    }

    @Test
    public void recreatesWithAllPrimitiveTypes(){
        WithAllPrimitiveTypes object = new WithAllPrimitiveTypes(true,
                'A',
                (short)2,
                3,
                4L,
                (float)5.6,
                7.8);
        assertRecreates(object);
    }

    public Map<String, Object> toJsonAndBack(TypedObject typedInt) {
        String json = gson.toJson(typedInt);
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
