package org.parrot.typed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

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
        TypedObject typedInt = typedObjectFactory.of(anInt);
        Map<String, Object> map = toJsonAndBack(typedInt);
        Object actual = objectFactory.of(map);
        Assert.assertEquals(anInt, actual);
    }

    @Test
    public void recreatesLong(){
        long aLong = 234L;
        TypedObject typedInt = typedObjectFactory.of(aLong);
        Map<String, Object> map = toJsonAndBack(typedInt);
        Object actual = objectFactory.of(map);
        Assert.assertEquals(aLong, actual);
    }

    @Test
    public void recreatesList(){
        List<Object> items = Arrays.asList(new Integer(12), new Long(34L));
        TypedObject typedList = typedObjectFactory.of(items);
        Map<String, Object> map = toJsonAndBack(typedList);
        Object actual = objectFactory.of(map);
        Assert.assertEquals(items, actual);
    }

    @Test
    public void recreatesMap(){
        Map<Object, Object> map = new HashMap<>();
        map.put(123, 456L);
        map.put(12L, 34);
        TypedObject typedList = typedObjectFactory.of(map);
        Map<String, Object> typedMap = toJsonAndBack(typedList);
        Object actual = objectFactory.of(typedMap);
        Assert.assertEquals(map, actual);
    }

    public Map<String, Object> toJsonAndBack(TypedObject typedInt) {
        String json = gson.toJson(typedInt);
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
