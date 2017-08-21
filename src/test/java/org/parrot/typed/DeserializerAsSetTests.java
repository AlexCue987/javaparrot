package org.parrot.typed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class DeserializerAsSetTests {
    final ObjectFactory objectFactory = new ObjectFactory();
    final DeserializerAsSet deserializer = new DeserializerAsSet(objectFactory);

    @Test
    public void works(){
        String json = "[{\"className\":\"java.lang.String\",\"persistingMethod\":\"VALUE\",\"value\":\"amber\"},{\"className\":\"java.lang.String\",\"persistingMethod\":\"VALUE\",\"value\":\"green\"}]";
        Gson gson = new Gson();
        Type type = new TypeToken<List<Map<String, Object>>>() {}.getType();
        Object mapFromJson = gson.fromJson(json, type);
        Object actual = deserializer.deserialize("java.util.ArrayList", mapFromJson);
        Object expected = new HashSet<String>(Arrays.asList("green", "amber"));
        Assert.assertEquals(expected, actual);
    }
}
