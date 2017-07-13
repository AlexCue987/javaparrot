package parrot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTests {

    @Test
    public void works() throws ClassNotFoundException {
        Gson gson = new Gson();
        Map<String, Object> attributes1 = new HashMap<>(2);
        attributes1.put("name", "box");
        attributes1.put("barCode", "123123");
        List<String> names1 = Arrays.asList("my best box", "box #1");
        NestedThing nestedThing = new NestedThing(names1, attributes1);
        Map<String, String> messageToSend = new HashMap<>(2);
        String name = nestedThing.getClass().getName();
        messageToSend.put("type", name);
        String payloadJson = gson.toJson(nestedThing);
        messageToSend.put("payload", payloadJson);

        String m = gson.toJson(nestedThing);
        Type messageType = new TypeToken<Map<String, String>>(){}.getType();
        String messageJson = gson.toJson(messageToSend, messageType);

        System.out.println(messageJson);

//        Type messageType = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, String> receivedMessage = gson.fromJson(messageJson, messageType);
//
        Class<?> clazz = Class.forName(receivedMessage.get("type").toString());
        String receivedPayloadStr = receivedMessage.get("payload");
//
//        String payloadJson = gson.toJson(payload);
        Type payloadType = getType(receivedMessage.get("type").toString());

        Type nestedThingType = new TypeToken<NestedThing>() {}.getType();
        Object typedPayload = gson.fromJson(payloadJson, payloadType);
        System.out.println(typedPayload);
    }

    static Type getType(String typeName){
        try {
            Class<?> clazz = Class.forName(typeName);
            TypeToken<?> typeToken = TypeToken.get(clazz);
            return typeToken.getType();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unsupported type: " + typeName);
        }
    }

    @Test
    public void works2() throws ClassNotFoundException {
        Map<String, Object> attributes1 = new HashMap<>(2);
        attributes1.put("name", "box");
        attributes1.put("barCode", "123123");
        List<String> names1 = Arrays.asList("my best box", "box #1");
        NestedThing nestedThing = new NestedThing(names1, attributes1);
        Map<String, Object> messageToSend = new HashMap<>(2);
        String name = nestedThing.getClass().getName();
        messageToSend.put("type", name);
        messageToSend.put("payload", nestedThing);

        Gson gson = new Gson();
        String messageJson = gson.toJson(messageToSend);
        System.out.println(messageJson);

        Type messageType = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> receivedMessage = gson.fromJson(messageJson, messageType);
        System.out.println(receivedMessage);
    }


    class TypeName implements Type{
        private final String name;

        TypeName(String name) {
            this.name = name;
        }

        @Override
        public String toString(){return name;}
    }
}
