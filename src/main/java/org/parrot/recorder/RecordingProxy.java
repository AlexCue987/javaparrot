package org.parrot.recorder;

import com.google.gson.Gson;
import org.parrot.InvocationInfo;
import org.parrot.storage.InvocationRecorder;
import org.parrot.storage.InvocationRecorderImpl;
import org.parrot.utils.CallerInfo;
import org.parrot.utils.StackReader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class RecordingProxy<T> implements InvocationHandler {
    private final T t;
    private final CallerInfo proxyUsedFrom;
    private final InvocationRecorder invocationRecorder;

    RecordingProxy(T t, CallerInfo proxyUsedFrom) {
        this.t = t;
        this.proxyUsedFrom = proxyUsedFrom;
        this.invocationRecorder = new InvocationRecorderImpl();
    }

    public T getProxy(Class<T> classToStub){
        @SuppressWarnings("unchecked")
        T ret = (T) Proxy.newProxyInstance(classToStub.getClassLoader(),
                new Class[]{classToStub}, this);
        return ret;
    }

    public static<T> T getProxy(T t, Class<T> classToStub){
        CallerInfo callerInfo = StackReader.getCallerInfo(1);
        RecordingProxy<T> proxy = new RecordingProxy<>(t, callerInfo);
        return proxy.getProxy(classToStub);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(t, args);
        Gson gson = new Gson();
        String resultStr = gson.toJson(result);
        String className = t.getClass().getSimpleName();
        Type genericReturnType = method.getGenericReturnType();
        Type[] parameterTypes = method.getGenericParameterTypes();
        CallerInfo callerInfo = new CallerInfo(className, method.getName());
        InvocationInfo invocationInfo = InvocationInfo.of(proxyUsedFrom,
                callerInfo,
                parameterTypes,
                args,
                result);
        System.out.println(invocationInfo);
        System.out.println(genericReturnType);
        Object result2 = gson.fromJson(resultStr, genericReturnType);
        if(result2 == null){
            System.out.println("null");
        }else {
            System.out.println(result2.toString());
            System.out.println(result2.getClass().toGenericString());
        }
        invocationRecorder.save(invocationInfo, result);
        return result2;
    }
}
