package org.parrot;

import com.google.gson.Gson;
import org.junit.Test;

public class IntegrationTests {
    @Test
    public void recreatesSimpleObject(){
        TypesTest typesTest = new TypesTest(true, 1, 2L, 3.4, "five");
        TypedInstanceFactory typedInstanceFactory = new TypedInstanceFactory();
        TypedInstance typedInstance = typedInstanceFactory.of(typesTest);
        Gson gson = new Gson();
        String json = gson.toJson(typedInstance);
        System.out.println(json);
        InstanceFactory instanceFactory = new InstanceFactory();
        Object obj = instanceFactory.of(json);
        System.out.println(obj);
    }
}
