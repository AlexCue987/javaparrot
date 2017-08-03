package org.parrot.storage;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.parrot.InvocationInfo;
import org.parrot.MethodCallParameter;
import org.parrot.recorder.RecordingProxy;
import org.parrot.utils.CallerInfo;

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
        List<MethodCallParameter> queryParameters = new ArrayList<>();
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
        ArrayList<MethodCallParameter> queryParameters = new ArrayList<>(2);
        queryParameters.add(new MethodCallParameter("Integer", new Integer(5)));
        queryParameters.add(new MethodCallParameter("BigDecimal", new BigDecimal("1.2")));
        InvocationInfo info = new InvocationInfo(proxyUsedFrom, callerInfo, queryParameters);
        InvocationRecorderImpl recorder = new InvocationRecorderImpl();
        recorder.save(info, "results");
    }

    @Test
    public void works2(){
        Box box = RecordingProxy.getProxy(new BoxImpl(), Box.class);
        box.getSize();
    }


}
