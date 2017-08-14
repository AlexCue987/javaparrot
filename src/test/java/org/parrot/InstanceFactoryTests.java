package org.parrot;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class InstanceFactoryTests {
    private final String simpleObjectJson = "{\n" +
            "  \"className\": \"org.parrot.Item\",\n" +
            "  \"fields\": [\n" +
            "    {\n" +
            "      \"type\": \"java.lang.String\",\n" +
            "      \"primitive\": true,\n" +
            "      \"value\": \"small blue item\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Ignore
    @Test
    public void works(){
        InstanceFactory instanceFactory = new InstanceFactory();
        Object actual = instanceFactory.of(simpleObjectJson);
//        System.out.println(actual);
        Assert.assertEquals("Item(itemName=small blue item)", actual.toString());
    }
}
