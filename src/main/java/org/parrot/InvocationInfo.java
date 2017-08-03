package org.parrot;

import lombok.*;
import org.parrot.utils.CallerInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    private final MethodCall methodCall;

    public static InvocationInfo of(CallerInfo proxyUsedFrom,
                                    CallerInfo callerInfo,
                                    Type[] argTypes,
                                    Object[] argValues,
                                    Object result){
        List<MethodCallParameter> queryParameters = toQueryParameters(argTypes, argValues);
        MethodCall methodCall  =new MethodCall(queryParameters, result);
        return new InvocationInfo(proxyUsedFrom, callerInfo, methodCall);
    }

    static List<MethodCallParameter> toQueryParameters(Type[] argTypes,
                                                 Object[] argValues){
        if(argTypes == null || argValues == null){
            return new ArrayList<>(0);
        }
        List<MethodCallParameter> ret = new ArrayList<>(argTypes.length);
        for(int i=0; i<argTypes.length; i++){
            MethodCallParameter pair = new MethodCallParameter(argTypes[i].getTypeName(), argValues[i]);
            ret.add(pair);
        }
        return ret;
    }
}
