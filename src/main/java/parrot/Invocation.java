package parrot;

import javafx.util.Pair;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Invocation{
    @NonNull
    private final String methodName;

    @NonNull
    private final List<Pair<String, Object>> queryParameters;

    public static Invocation of(Method method, Object[] args){
        String methodName = method.getName();
        List<Pair<String, Object>> queryParameters = toQueryParameters(args);
        return new Invocation(methodName, queryParameters);
    }

    static List<Pair<String, Object>> toQueryParameters(Object[] args){
        return Arrays.stream(args).
                map(arg -> new Pair<>(arg.getClass().getName(), arg)).
                collect(Collectors.toList());
    }
}
