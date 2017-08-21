package org.parrot.typed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Map;

public class ObjectFactoryTests {
    ObjectFactory factory = new ObjectFactory();

    @Test
    public void works(){
        String json = "{\"className\":\"java.lang.String\",\"persistingMethod\":\"VALUE\",\"value\":\"shoe\"}";
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> mapFromJson = gson.fromJson(json, type);
        Object actual = factory.of(mapFromJson);
        Assert.assertEquals("shoe", actual.toString());
    }

    @Test
    public void handlesNullMap(){
        Map<String, Object> mapFromJson = null;
        Object actual = factory.of(mapFromJson);
        Assert.assertTrue(actual == null);
    }

    @Test
    public void handlesNullValue(){
        String json = "{\"className\":\"java.lang.String\",\"persistingMethod\":\"VALUE\"}";
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> mapFromJson = gson.fromJson(json, type);
        Object actual = factory.of(mapFromJson);
        Assert.assertTrue(actual == null);
    }
}
