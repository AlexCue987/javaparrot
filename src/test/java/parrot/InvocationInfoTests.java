package parrot;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class InvocationInfoTests {
    private final String QUERY_PARAMETERS_STR = "NameValuePair(name=java.lang.String, value=My String, typeName=anyname)\n" +
            "NameValuePair(name=java.lang.Integer, value=42, typeName=anyname)\n" +
            "NameValuePair(name=java.lang.Long, value=123, typeName=anyname)";

    @Test
    public void convertArgs_works(){
        Object[] args = getArgs();
        List<NameValuePair> queryParameters = InvocationInfo.toQueryParameters(args);
        String actual = queryParametersToString(queryParameters);
        System.out.println(actual);
        Assert.assertEquals(QUERY_PARAMETERS_STR, actual);
    }

    private String queryParametersToString(List<NameValuePair> queryParameters) {
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
