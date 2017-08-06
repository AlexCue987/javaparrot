package org.parrot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TypedInstanceFactoryTests {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    Box smallBox = new Box("small",
            Arrays.asList(new Item("USB stick")),
            new ArrayList<>());
    Box mediumBox = new Box("medium",
            Arrays.asList(new Item("flip-flops"),
                    new Item("sneakers")),
            new ArrayList<>());
    Box bigBox = new Box("big",
            Arrays.asList(new Item("big old book"),
                    new Item("blank DVD")),
            Arrays.asList(smallBox, mediumBox));

    @Test
    public void works() throws IllegalAccessException {
        TypedInstance instance = new TypedInstanceFactory().of(new TypesTest(false, 1, 2L, 3.4, "five"));
        System.out.println(instance);
        String json = gson.toJson(instance);
        System.out.println(json);
    }

    @Test
    public void nested() throws IllegalAccessException {
        TypesNestedTest t1 = new TypesNestedTest(false, 1, 2L, 3.4, "five", null);
        TypesNestedTest t2 = new TypesNestedTest(true, 2, 3L, 4.5, "six", t1);
        TypedInstance instance = new TypedInstanceFactory().of(t2);
        String s = instance.toString().replace(",", "\n,");
        System.out.println(s);
        String json = gson.toJson(instance);
        System.out.println(json);
    }

    @Test
    public void worksWithLists(){
        TypedInstance instance = new TypedInstanceFactory().of(bigBox);
        String s = instance.toString().replace(",", "\n,");
        System.out.println(s);
        String json = gson.toJson(instance);
        System.out.println(json);
    }

    @Test
    public void ofMap_handlesNull(){
        TypedField typedField = new TypedInstanceFactory().ofMap(null);
        String json = gson.toJson(typedField);
        System.out.println(json);
        String expected = "{\n" +
                "  \"type\": \"java.util.Map\"\n" +
                "}";
        Assert.assertEquals(expected, json);
    }

    @Test
    public void ofMap_works(){
        Map<Object, Object> map = new HashMap<>();
        map.put(smallBox, "nothing");
        map.put("31.14159", mediumBox);
        TypedField typedField = new TypedInstanceFactory().ofMap(map);
        String json = gson.toJson(typedField);
        System.out.println(json);
        String expected = "{\n" +
                "  \"type\": \"java.util.HashMap\",\n" +
                "  \"value\": [\n" +
                "    {\n" +
                "      \"key\": {\n" +
                "        \"className\": \"class java.lang.String\",\n" +
                "        \"fields\": [\n" +
                "          {\n" +
                "            \"type\": \"class java.lang.String\",\n" +
                "            \"value\": \"31.14159\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"value\": {\n" +
                "        \"className\": \"org.parrot.Box\",\n" +
                "        \"fields\": [\n" +
                "          {\n" +
                "            \"type\": \"java.lang.String\",\n" +
                "            \"value\": \"medium\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"java.util.Arrays$ArrayList\",\n" +
                "            \"value\": [\n" +
                "              {\n" +
                "                \"className\": \"org.parrot.Item\",\n" +
                "                \"fields\": [\n" +
                "                  {\n" +
                "                    \"type\": \"java.lang.String\",\n" +
                "                    \"value\": \"flip-flops\"\n" +
                "                  }\n" +
                "                ]\n" +
                "              },\n" +
                "              {\n" +
                "                \"className\": \"org.parrot.Item\",\n" +
                "                \"fields\": [\n" +
                "                  {\n" +
                "                    \"type\": \"java.lang.String\",\n" +
                "                    \"value\": \"sneakers\"\n" +
                "                  }\n" +
                "                ]\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"java.util.ArrayList\",\n" +
                "            \"value\": []\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"key\": {\n" +
                "        \"className\": \"org.parrot.Box\",\n" +
                "        \"fields\": [\n" +
                "          {\n" +
                "            \"type\": \"java.lang.String\",\n" +
                "            \"value\": \"small\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"java.util.Arrays$ArrayList\",\n" +
                "            \"value\": [\n" +
                "              {\n" +
                "                \"className\": \"org.parrot.Item\",\n" +
                "                \"fields\": [\n" +
                "                  {\n" +
                "                    \"type\": \"java.lang.String\",\n" +
                "                    \"value\": \"USB stick\"\n" +
                "                  }\n" +
                "                ]\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"java.util.ArrayList\",\n" +
                "            \"value\": []\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"value\": {\n" +
                "        \"className\": \"class java.lang.String\",\n" +
                "        \"fields\": [\n" +
                "          {\n" +
                "            \"type\": \"class java.lang.String\",\n" +
                "            \"value\": \"nothing\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Assert.assertEquals(expected, json);
    }
}
