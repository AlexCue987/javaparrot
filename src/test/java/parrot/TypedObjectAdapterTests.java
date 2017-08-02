package parrot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TypedObjectAdapterTests {
    private final Gson gson = new GsonBuilder().
            registerTypeHierarchyAdapter(Object.class, new TypedObjectAdapter()).
            create();

    @Ignore
    @Test
    public void works(){
        List<Object> value = getList();
        String str = gson.toJson(value);
        System.out.println(str);
    }


    private List<Object> getList() {
        List<Object> value = new ArrayList<>(3);
        value.add("Some String");
        value.add(123L);
        value.add(345);
        return value;
    }
}
