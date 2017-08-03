package org.parrot.recorder;

import org.junit.Test;
import org.parrot.utils.CallerInfo;
import org.parrot.utils.StackReader;

public class RecordingProxyTests {
    @Test
    public void works(){
        CallerInfo callerInfo = StackReader.getCallerInfo(0);
        RecordingProxy<Box> boxProxy = new RecordingProxy<>(new BoxImpl(), callerInfo);
        Box box = boxProxy.getProxy(Box.class);
        box.getSize();
    }

    @Test
    public void works2(){
        Box box = RecordingProxy.getProxy(new BoxImpl(), Box.class);
        box.getSize();
    }

    @Test
    public void concat(){
        Concatenator concatenator = RecordingProxy.getProxy(new ConcatenatorImpl(), Concatenator.class);
        concatenator.concatLen(new BoxImpl(), new Long(1234L));
        concatenator.getMyResult("Opps!");
    }

    @Test
    public void list(){
        Concatenator concatenator = RecordingProxy.getProxy(new ConcatenatorImpl(), Concatenator.class);
        concatenator.join(123L, "Why?");
    }

}
