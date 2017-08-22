package org.parrot.typed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

public class DeserializerAsArrayTests {
    private final ObjectFactory objectFactory = new ObjectFactory();
    private final DeserializerAsArray deserializer = new DeserializerAsArray(objectFactory);

    @Test
    public void works(){
        String json = "[{\"className\":\"java.lang.Integer\",\"persistingMethod\":\"VALUE\",\"value\":\"1\"},{\"className\":\"java.lang.Long\",\"persistingMethod\":\"VALUE\",\"value\":\"2\"},{\"className\":\"java.lang.String\",\"persistingMethod\":\"VALUE\",\"value\":\"three\"}]";
        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {}.getType();
        Object mapFromJson = gson.fromJson(json, type);
        Object[] actual = (Object[])deserializer.deserialize("java.lang.Object", mapFromJson);
        Object[] expected = new Object[]{1, 2L, "three"};
        Assert.assertArrayEquals(expected, actual);
    }
}
