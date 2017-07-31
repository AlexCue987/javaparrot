package parrot;

import lombok.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypedValue {
    @NonNull
    private final String typeName;
    @NonNull
    private final String jsonString;

    private static final Gson gson = getGson();

    public static TypedValue of(Object value){
        String jsonValue = getJsonValue(value);
        return new TypedValue(value.getClass().getTypeName(), jsonValue);
    }

    private static String getJsonValue(Object value) {
        if(value instanceof String || value instanceof Long || value instanceof Integer){
            return value.toString();
        }
        return gson.toJson(value);
    }

    private static Gson getGson(){
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeHierarchyAdapter(Object.class, new TypedObjectAdapter())
                .disableHtmlEscaping()
                .create();
    }

    public Object getValue(){
        Type payloadType = getType(typeName);
        Object typedValue = gson.fromJson(jsonString, payloadType);
        return typedValue;
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
}
