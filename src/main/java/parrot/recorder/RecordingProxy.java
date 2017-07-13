package parrot.recorder;

import com.google.gson.Gson;
import parrot.InvocationInfo;
import parrot.utils.CallerInfo;
import parrot.utils.StackReader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class RecordingProxy<T> implements InvocationHandler {
    private final T t;
    private final CallerInfo proxyUsedFrom;

    RecordingProxy(T t, CallerInfo proxyUsedFrom) {
        this.t = t;
        this.proxyUsedFrom = proxyUsedFrom;
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
        CallerInfo callerInfo = new CallerInfo(className, method.getName());
        InvocationInfo invocationInfo = InvocationInfo.of(method, args);
//            if(genericReturnType.toString().equals("java.util.Map<java.lang.String, java.util.List<java.lang.String>>")){
//                String json = "{\"name\":[\"123\", \"456\"]}";
//                Map<String, List<String>> ret = gson.fromJson(json, genericReturnType);
//                System.out.println(ret);
//            }
        System.out.println(method.getName() + " " + method.getReturnType() + " " + genericReturnType);
        Object result2 = gson.fromJson(resultStr, genericReturnType);
        if(result2 == null){
            System.out.println("null");
        }else {
            System.out.println(result2.toString());
            System.out.println(result2.getClass().toGenericString());
        }
        return result2;
    }
}
