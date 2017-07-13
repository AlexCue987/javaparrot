package parrot;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class InvocationInfoTests {
    private final String QUERY_PARAMETERS_STR = "java.lang.String=My String\n" +
            "java.lang.Integer=42\n" +
            "java.lang.Long=123";

    @Test
    public void convertArgs_works(){
        Object[] args = getArgs();
        List<Pair<String, Object>> queryParameters = InvocationInfo.toQueryParameters(args);
        String actual = queryParametersToString(queryParameters);
//        System.out.println(actual);
        Assert.assertEquals(QUERY_PARAMETERS_STR, actual);
    }

    private String queryParametersToString(List<Pair<String, Object>> queryParameters) {
        return queryParameters.stream().
                map(Object::toString).
                collect(Collectors.joining("\n"));
    }

    private Object[] getArgs() {
        Object[] args = new Object[3];
        args[0] = "My String";
        args[1] = 42;
        args[2] = 123L;
        return args;
    }
}
