package parrot.storage;


import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;
import parrot.InvocationInfo;
import parrot.utils.CallerInfo;

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
        List<Pair<String, Object>> queryParameters = new ArrayList<>();
        final InvocationInfo invocationInfo = new InvocationInfo(proxyUsedFrom, callerInfo, queryParameters);
        String actual = invocationRecorder.getFileName(invocationInfo);
        Assert.assertEquals("src/test/javaparrot/TestClass/TestMethod/StubbedClass/StubbedMethod.json", actual.toString());
    }

    @Test
    public void addFoldersToBasePath_works(){
        Path actual = invocationRecorder.addFoldersToBasePath(basePathStr, proxyUsedFrom, callerInfo);
        Assert.assertEquals("basePathStr/TestClass/TestMethod/StubbedClass", actual.toString());
    }

    @Test
    public void getInvocationInfoList_works(){
        List<InvocationInfo> actual = new InvocationRecorderImpl().getInvocationInfoList("[]");
        System.out.println(actual);
        Assert.assertEquals(0, actual.size());
    }
}
