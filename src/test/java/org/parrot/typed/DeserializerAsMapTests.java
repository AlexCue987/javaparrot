package org.parrot.typed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

public class DeserializerAsMapTests {
    final ObjectFactory objectFactory = new ObjectFactory();
    final DeserializerAsMap deserializer = new DeserializerAsMap(objectFactory);

    @Test
    public void works(){
        String json = "[{\"key\":{\"className\":\"java.lang.String\",\"persistingMethod\":\"VALUE\",\"value\":\"asdf\"}," +
                "\"value\":{\"className\":\"org.parrot.testobjects.Thing\",\"persistingMethod\":\"FIELDS\",\"value\":[{\"fieldName\":\"name\",\"typedValue\":{\"className\":\"java.lang.String\",\"persistingMethod\":\"VALUE\",\"value\":\"shoe\"}},{\"fieldName\":\"size\",\"typedValue\":{\"className\":\"java.lang.Integer\",\"persistingMethod\":\"VALUE\",\"value\":\"12\"}}]}}," +
                "{\"key\":{\"className\":\"org.parrot.testobjects.Thing\",\"persistingMethod\":\"FIELDS\",\"value\":[{\"fieldName\":\"name\",\"typedValue\":{\"className\":\"java.lang.String\",\"persistingMethod\":\"VALUE\",\"value\":\"wrench\"}},{\"fieldName\":\"size\",\"typedValue\":{\"className\":\"java.lang.Integer\",\"persistingMethod\":\"VALUE\",\"value\":\"10\"}}]}," +
                "\"value\":{\"className\":\"java.lang.Long\",\"persistingMethod\":\"VALUE\",\"value\":\"234\"}}]";
        Gson gson = new Gson();
        Type type = new TypeToken<List<Object>>() {}.getType();
        Object mapFromJson = gson.fromJson(json, type);
        Object actual = deserializer.deserialize("java.util.ArrayList", mapFromJson);
        Assert.assertEquals("{asdf=Thing(name=shoe, size=12), Thing(name=wrench, size=10)=234}", actual.toString());
    }
}
