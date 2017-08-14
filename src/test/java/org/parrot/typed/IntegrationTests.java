package org.parrot.typed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
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

    public Map<String, Object> toJsonAndBack(TypedObject typedInt) {
        String json = gson.toJson(typedInt);
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
