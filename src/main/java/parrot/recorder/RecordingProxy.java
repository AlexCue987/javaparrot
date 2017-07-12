package parrot.recorder;

import com.google.gson.Gson;
import parrot.InvocationInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class RecordingProxy<T> implements InvocationHandler {
    private final T t;

    RecordingProxy(T t) {
        this.t = t;
    }

    public T getProxy(Class<T> classToStub){
        @SuppressWarnings("unchecked")
        T ret = (T) Proxy.newProxyInstance(classToStub.getClassLoader(),
                new Class[]{classToStub}, this);
        return ret;
    }

    public static<T> T getProxy(T t, Class<T> classToStub){
        RecordingProxy<T> proxy = new RecordingProxy<>(t);
        return proxy.getProxy(classToStub);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(t, args);
        Gson gson = new Gson();
        String resultStr = gson.toJson(result);
        Type genericReturnType = method.getGenericReturnType();
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
