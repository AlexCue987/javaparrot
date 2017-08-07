package org.parrot;

import org.junit.Test;

public class InstanceFactoryTests {
    private final String simpleObjectJson = "{\n" +
            "  \"className\": \"org.parrot.Item\",\n" +
            "  \"fields\": [\n" +
            "    {\n" +
            "      \"type\": \"java.lang.String\",\n" +
            "      \"value\": \"small blue item\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";

    @Test
    public void works(){
        new InstanceFactory().of(simpleObjectJson);
    }
}
