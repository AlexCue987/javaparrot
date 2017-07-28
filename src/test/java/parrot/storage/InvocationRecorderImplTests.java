package parrot.storage;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import parrot.InvocationInfo;
import parrot.NameValuePair;
import parrot.utils.CallerInfo;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InvocationRecorderImplTests {
    final String basePathStr = "basePathStr";
    final CallerInfo proxyUsedFrom = new CallerInfo("TestClass", "TestMethod");
    final CallerInfo callerInfo = new CallerInfo("StubbedClass", "StubbedMethod");

    private final InvocationRecorderImpl invocationRecorder = new InvocationRecorderImpl();

    @Test
    public void getFileName_works(){
        List<NameValuePair> queryParameters = new ArrayList<>();
        final InvocationInfo invocationInfo = new InvocationInfo(proxyUsedFrom, callerInfo, queryParameters);
        String actual = invocationRecorder.getFileName(invocationInfo);
        Assert.assertEquals("src/test/resources/javaparrot/TestClass/TestMethod/StubbedClass/StubbedMethod.json", actual.toString());
    }

    @Test
    public void addFoldersToBasePath_works(){
        Path actual = invocationRecorder.addFoldersToBasePath(basePathStr, proxyUsedFrom, callerInfo);
        Assert.assertEquals("basePathStr/TestClass/TestMethod/StubbedClass", actual.toString());
    }

    @Test
    public void getInvocationInfoList_works(){
        List<InvocationInfo> actual = new InvocationRecorderImpl().parseJson("[]");
        System.out.println(actual);
        Assert.assertEquals(0, actual.size());
    }

    @Ignore
    @Test
    public void works(){
        CallerInfo proxyUsedFrom = new CallerInfo("MyProxyClass", "myProxyMethod");
        CallerInfo callerInfo = new CallerInfo("MyClass", "myMethod");
        ArrayList<NameValuePair> queryParameters = new ArrayList<>(2);
        queryParameters.add(new NameValuePair("length", new Integer(5), Integer.class.getName()));
        queryParameters.add(new NameValuePair("weight", new BigDecimal("1.2"), BigDecimal.class.getName()));
        InvocationInfo info = new InvocationInfo(proxyUsedFrom, callerInfo, queryParameters);
        InvocationRecorderImpl recorder = new InvocationRecorderImpl();
        recorder.save(info, "results");
    }
}
