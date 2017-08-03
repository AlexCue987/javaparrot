package org.parrot;

import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class InvocationInfoTests {
    private final String QUERY_PARAMETERS_STR = "MethodCallParameter(declaredType=java.lang.String, submittedValue=My String)\n" +
            "MethodCallParameter(declaredType=java.lang.Integer, submittedValue=42)\n" +
            "MethodCallParameter(declaredType=java.lang.Object, submittedValue=123)";

    @Test
    public void convertArgs_works(){
        Object[] args = getArgs();
        Type stringType = new TypeToken<String>(){}.getType();
        Type intType = new TypeToken<Integer>(){}.getType();
        Type longType = new TypeToken<Object>(){}.getType();
        Type[] argTypes = {stringType, intType, longType};
        List<MethodCallParameter> queryParameters = InvocationInfo.toQueryParameters(argTypes, args);
        String actual = queryParametersToString(queryParameters);
        System.out.println(actual);
        Assert.assertEquals(QUERY_PARAMETERS_STR, actual);
    }

    private String queryParametersToString(List<MethodCallParameter> queryParameters) {
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
