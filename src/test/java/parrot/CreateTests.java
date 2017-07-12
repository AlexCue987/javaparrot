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
        String m = gson.toJson(nestedThing);
        String message = gson.toJson(messageToSend);

        Type messageType = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> receivedMessage = gson.fromJson(message, messageType);

        Class<?> clazz = Class.forName(receivedMessage.get("type").toString());
        Object payload = receivedMessage.get("payload");

        String payloadJson = gson.toJson(payload);
//        Type payloadType = new TypeName(receivedMessage.get("type").toString()){};

        Object typedPayload = gson.fromJson(payloadJson, clazz);
        System.out.println(typedPayload);

    }
}
