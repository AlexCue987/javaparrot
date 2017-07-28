package parrot;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import parrot.utils.CallerInfo;

import java.lang.reflect.Type;
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

    public static InvocationInfo of(CallerInfo proxyUsedFrom,
                                    CallerInfo callerInfo,
                                    Type[] argTypes,
                                    Object[] argValues){
        List<NameValuePair> queryParameters = toQueryParameters(argTypes, argValues);
        return new InvocationInfo(proxyUsedFrom, callerInfo, queryParameters);
    }

    static List<NameValuePair> toQueryParameters(Type[] argTypes,
                                                 Object[] argValues){
        if(argTypes == null || argValues == null){
            return new ArrayList<>(0);
        }
        List<NameValuePair> ret = new ArrayList<>(argTypes.length);
        for(int i=0; i<argTypes.length; i++){
            String s1 = argTypes[i].getTypeName();
            String s2 = argTypes[i].getClass().getName();
            NameValuePair pair = new NameValuePair(argTypes[i].getTypeName(), argValues[i], argValues[i].getClass().getName());
            ret.add(pair);
        }
        return ret;
    }
}
