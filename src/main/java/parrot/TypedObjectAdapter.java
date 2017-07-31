package parrot;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class TypedObjectAdapter extends TypeAdapter<Object> {
    @Override
    public void write(JsonWriter out, Object o) throws IOException {
        TypedValue typedValue = (o instanceof TypedValue) ? (TypedValue) o : TypedValue.of(o);
        out.beginObject();
        out.name("type").value(typedValue.getTypeName());
        out.name("value").value(typedValue.getJsonString());
        out.endObject();
    }

    @Override
    public Object read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
