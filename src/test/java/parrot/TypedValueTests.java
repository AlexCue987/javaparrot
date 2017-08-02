package parrot;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TypedValueTests {
    @Ignore
    @Test
    public void worksForList(){
        List<Object> value = getList();
        TypedValue typedValue = TypedValue.of(value);
        Object actual = typedValue.getValue();
//        Assert.assertEquals(value, actual);
    }

    private List<Object> getList() {
        List<Object> value = new ArrayList<>(3);
        value.add("Some String");
        value.add(123L);
        value.add(345);
        return value;
    }
}
