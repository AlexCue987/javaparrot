package org.parrot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.parrot.testobjects.WithNestedObject;
import org.parrot.testobjects.TypesTest;

import java.lang.reflect.Field;
import java.util.*;

@Ignore
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
    public void worksWithItem(){
        TypedInstance instance = new TypedInstanceFactory().of(new Item("small blue item"));
        System.out.println(instance);
        String json = gson.toJson(instance);
        System.out.println(json);

    }

    @Test
    public void getFieldsToSave_handlesInteger(){
        List<Field> fields = new TypedInstanceFactory().getFieldsToSave(new Integer(123));
//        fields.forEach(System.out::println);
        Assert.assertEquals("[private final int java.lang.Integer.value]", fields.toString());
    }

    @Test
    public void getFieldsToSave_handlesLong(){
        List<Field> fields = new TypedInstanceFactory().getFieldsToSave(new Long(1234L));
//        fields.forEach(System.out::println);
        Assert.assertEquals("[private final long java.lang.Long.value]", fields.toString());
    }

    @Test
    public void nested() throws IllegalAccessException {
        WithNestedObject t1 = new WithNestedObject(false, 1, 2L, 3.4, "five", null);
        WithNestedObject t2 = new WithNestedObject(true, 2, 3L, 4.5, "six", t1);
        TypedInstance instance = new TypedInstanceFactory().of(t2);
        String s = instance.toString().replace(",", "\n,");
        System.out.println(s);
        String json = gson.toJson(instance);
        System.out.println(json);
    }

    @Test
    public void ofList_works(){
        TypedInstance instance = new TypedInstanceFactory().of(mediumBox);
        String s = instance.toString().replace(",", "\n,");
        System.out.println(s);
        String json = gson.toJson(instance);
//        System.out.println(json);
        String expected = "{\n" +
                "  \"className\": \"org.parrot.Box\",\n" +
                "  \"fields\": [\n" +
                "    {\n" +
                "      \"type\": \"java.lang.String\",\n" +
                "      \"value\": \"medium\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"java.util.Arrays$ArrayList\",\n" +
                "      \"value\": [\n" +
                "        {\n" +
                "          \"className\": \"org.parrot.Item\",\n" +
                "          \"fields\": [\n" +
                "            {\n" +
                "              \"type\": \"java.lang.String\",\n" +
                "              \"value\": \"flip-flops\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"className\": \"org.parrot.Item\",\n" +
                "          \"fields\": [\n" +
                "            {\n" +
                "              \"type\": \"java.lang.String\",\n" +
                "              \"value\": \"sneakers\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"java.util.ArrayList\",\n" +
                "      \"value\": []\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Assert.assertEquals(expected, json);
    }

    @Test
    public void ofList_handlesNull(){
        TypedField typedField = new TypedInstanceFactory().ofList(null, "list");
        String json = gson.toJson(typedField);
        System.out.println(json);
        String expected = "{\n" +
                "  \"type\": \"java.util.List\"\n" +
                "}";
        Assert.assertEquals(expected, json);
    }

    @Test
    public void ofMap_handlesNull(){
        TypedField typedField = new TypedInstanceFactory().ofMap(null, "map");
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
        TypedField typedField = new TypedInstanceFactory().ofMap(map, "map");
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

    @Test
    public void ofSet_handlesNulls(){
        Set<Object> set = null;
        TypedField typedField = new TypedInstanceFactory().ofSet(set, "set");
        String json = gson.toJson(typedField);
        System.out.println(json);
        String expected = "{\n" +
                "  \"type\": \"java.util.Set\"\n" +
                "}";
        Assert.assertEquals(expected, json);
    }

    @Test
    public void ofSet_works(){
        Set<Object> set = new HashSet<>();
        set.add(new Long(12L));
        set.add(new Integer(34));
        TypedField typedField = new TypedInstanceFactory().ofSet(set, "set");
        String json = gson.toJson(typedField);
        System.out.println(json);
        String expected = "{\n" +
                "  \"type\": \"java.util.HashSet\",\n" +
                "  \"value\": [\n" +
                "    {\n" +
                "      \"className\": \"class java.lang.Long\",\n" +
                "      \"fields\": [\n" +
                "        {\n" +
                "          \"type\": \"class java.lang.Long\",\n" +
                "          \"value\": 12\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"className\": \"class java.lang.Integer\",\n" +
                "      \"fields\": [\n" +
                "        {\n" +
                "          \"type\": \"class java.lang.Integer\",\n" +
                "          \"value\": 34\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Assert.assertEquals(expected, json);
    }
}
