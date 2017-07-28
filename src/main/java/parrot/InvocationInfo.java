package parrot;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
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
    private final List<NameValuePair> queryParameters;

    public static InvocationInfo of(CallerInfo proxyUsedFrom, CallerInfo callerInfo, Object[] args){
        List<NameValuePair> queryParameters = toQueryParameters(args);
        return new InvocationInfo(proxyUsedFrom, callerInfo, queryParameters);
    }

    static List<NameValuePair> toQueryParameters(Object[] args){
        if(args == null){
            return new ArrayList<>(0);
        }
        return Arrays.stream(args).
                map(arg -> new NameValuePair(arg.getClass().getName(), arg, "anyname")).
                collect(Collectors.toList());
    }
}
