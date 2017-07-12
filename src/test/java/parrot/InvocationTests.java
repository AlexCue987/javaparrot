package parrot;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class InvocationTests {
    @Test
    public void convertArgs_works(){
        Object[] args = getArgs();
        List<Pair<String, Object>> queryParameters = Invocation.toQueryParameters(args);
        String actual = queryParameters.stream().
                map(Object::toString).
                collect(Collectors.joining("\n"));
//        System.out.println(actual);
        String expected = "java.lang.String=My String\n" +
                "java.lang.Integer=42\n" +
                "java.lang.Long=123";
        Assert.assertEquals(expected, actual);
    }

    private Object[] getArgs() {
        Object[] args = new Object[3];
        args[0] = "My String";
        args[1] = 42;
        args[2] = 123L;
        return args;
    }

}
