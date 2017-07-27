package parrot;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import parrot.utils.CallerInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class InvocationInfo {
    @NonNull
    private final CallerInfo proxyUsedFrom;
    @NonNull
    private final CallerInfo callerInfo;

    @NonNull
    private final List<Pair<String, Object>> queryParameters;

    public static InvocationInfo of(CallerInfo proxyUsedFrom, CallerInfo callerInfo, Object[] args){
        List<Pair<String, Object>> queryParameters = toQueryParameters(args);
        return new InvocationInfo(proxyUsedFrom, callerInfo, queryParameters);
    }

    static List<Pair<String, Object>> toQueryParameters(Object[] args){
        if(args == null){
            return new ArrayList<>(0);
        }
        return Arrays.stream(args).
                map(arg -> new ImmutablePair<>(arg.getClass().getName(), arg)).
                collect(Collectors.toList());
    }
}
