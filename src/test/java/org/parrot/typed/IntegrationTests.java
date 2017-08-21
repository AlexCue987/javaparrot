package org.parrot.typed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;
import org.parrot.testobjects.*;

import java.lang.reflect.Type;
import java.util.*;

public class IntegrationTests {
    TypedObjectFactory typedObjectFactory = new TypedObjectFactory();
    Gson gson = new Gson();
    ObjectFactory objectFactory = new ObjectFactory();

    WithNestedObject nestedObject = null;
    WithNestedObject innerObject = new WithNestedObject(true, 1 ,2L, 3.4, "five", nestedObject);
    WithNestedObject outerObject = new WithNestedObject(false, 2 ,2L, 4.5, "six", innerObject);
    WithAllPrimitiveTypes withAllPrimitiveTypes = new WithAllPrimitiveTypes(true,
            'A',
            (short)2,
            3,
            4L,
            (float)5.6,
            7.8);

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
    public void recreatesSet(){
        List<Object> items = Arrays.asList("wrench", "saw");
        Set<Object> set = new HashSet<>(items);
        assertRecreates(set);
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
        assertRecreates(withAllPrimitiveTypes);
    }

    @Test
    public void recreatesWithNestedObject(){
        assertRecreates(outerObject);
    }

    @Test
    public void recreates_WithListOfThings_nullList(){
        List<Thing> things = null;
        WithListOfThings withListOfThings = new WithListOfThings("with null list", 0, things);
        assertRecreates(withListOfThings);
    }

    @Test
    public void recreates_WithListOfThings_emptyList(){
        List<Thing> things = new ArrayList<>();
        WithListOfThings withListOfThings = new WithListOfThings("with null list", 0, things);
        assertRecreates(withListOfThings);
    }

    @Test
    public void recreatesWithListOfThings_populatedList() {
        List<Thing> things = Arrays.asList(new Thing("shoe", 12), new Thing("wrench", 10));
        WithListOfThings withListOfThings = new WithListOfThings("with populated list", 0, things);
        assertRecreates(withListOfThings);
    }

    @Test
    public void recreatesWithArrayListOfThings_populatedList() {
        ArrayList<Thing> things = new ArrayList<>(Arrays.asList(new Thing("shoe", 12), new Thing("wrench", 10)));
        WithArrayListOfThings withArrayListOfThings = new WithArrayListOfThings("with populated list", 0, things);
        assertRecreates(withArrayListOfThings);
    }

    @Test
    public void recreatesWithListOfObjects_populatedListOfDifferentTypes() {
        List<Object> things = Arrays.asList(new Thing("shoe", 12), 23L, 4.56, "aString",
                outerObject, withAllPrimitiveTypes);
        WithListOfObjects withListOfObjects = new WithListOfObjects("with list of different things", 0, things);
        assertRecreates(withListOfObjects);
    }

    @Test
    public void recreates_WithMapOfThings_nullMap(){
        Map<Object, Object> things = null;
        WithMapOfThings withMapOfThings = new WithMapOfThings("with null Map", 0, things);
        assertRecreates(withMapOfThings);
    }

    @Test
    public void recreates_WithMapOfThings_emptyMap(){
        Map<Object, Object> things = new HashMap<>();
        WithMapOfThings withMapOfThings = new WithMapOfThings("with null Map", 0, things);
        assertRecreates(withMapOfThings);
    }

    @Test
    public void recreatesWithMapOfThings_populatedMap() {
        Map<Object, Object> things = new HashMap<>();
        things.put("asdf", new Thing("shoe", 12));
        things.put(new Thing("wrench", 10), 234L);
        WithMapOfThings withMapOfThings = new WithMapOfThings("with populated Map", 0, things);
        assertRecreates(withMapOfThings);
    }

    @Test
    public void recreatesWithHashMapOfThings_populatedMap() {
        HashMap<Object, Object> things = new HashMap<>();
        things.put("asdf", new Thing("shoe", 12));
        things.put(new Thing("wrench", 10), 234L);
        WithHashMapOfThings withMapOfThings = new WithHashMapOfThings("with populated Map", 0, things);
        assertRecreates(withMapOfThings);
    }

    public Map<String, Object> toJsonAndBack(TypedObject typedInt) {
        String json = gson.toJson(typedInt);
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
