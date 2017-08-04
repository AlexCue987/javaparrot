package org.parrot;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.parrot.recorder.MyResult;
import org.parrot.recorder.MyResultList;

import java.io.IOException;
import java.util.*;

public class JacksonTests {
    @Test
    public void works() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // enable pretty printing
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.WRAPPER_ARRAY);
        mapper.registerSubtypes(String.class, Integer.class);

        // serialize the object
//        mapper.writeValue(System.out, getList());
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getList());
        System.out.println(json);
        List<Object> myObjects = mapper.readValue(json, new TypeReference<List<Object>>(){});
        for(Object object: myObjects){
            System.out.println(object.getClass().toString());
            System.out.println(object);
        }
    }

    @Test
    public void doesNotSaveNestedTypes() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.WRAPPER_ARRAY);
        mapper.registerSubtypes(MyResultList.class, MyResult.class);
        ObjectWriter objectWriter = mapper.writerWithDefaultPrettyPrinter();
        MyResultList myResultList = getMyResultList();
        String json = objectWriter.writeValueAsString(myResultList);
        System.out.println(json);
    }

    private MyResultList getMyResultList(){
        List<MyResult> myList = new ArrayList<>(3);
        myList.add(new MyResult(1));
        myList.add(new MyResult(2));
        return new MyResultList(myList);
    }

    private List<Object> getList() {
        List<Object> value = new ArrayList<>(3);
        value.add("Some String");
        value.add(123L);
        value.add(345);
        value.add(CreateTests.getNestedThing());
        value.add(getVeryNestedThing());
        return value;
    }

    VeryNestedThing getVeryNestedThing(){
        NestedThing nestedThing = CreateTests.getNestedThing();
        List<NestedThing> list = Arrays.asList(nestedThing);
        Map<String, NestedThing> map = new HashMap<>(1);
        map.put("my key", nestedThing);
        return new VeryNestedThing(list, map);
    }

}
