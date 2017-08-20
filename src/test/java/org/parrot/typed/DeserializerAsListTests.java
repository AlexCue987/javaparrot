package org.parrot.typed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class DeserializerAsListTests {
    final ObjectFactory objectFactory = new ObjectFactory();
    final DeserializerAsList deserializer = new DeserializerAsList(objectFactory);

    @Test
    public void works(){
        String json = "[{\"className\":\"java.lang.Integer\",\"persistingMethod\":\"VALUE\",\"value\":\"12\"}," +
                "{\"className\":\"java.lang.Long\",\"persistingMethod\":\"VALUE\",\"value\":\"34\"}]";
        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {}.getType();
        Object mapFromJson = gson.fromJson(json, type);
        Object actual = deserializer.deserialize("java.util.ArrayList", mapFromJson);
        List<Object> expected = Arrays.asList(new Integer(12), new Long(34L));
        Assert.assertEquals(expected, actual);
    }
}
