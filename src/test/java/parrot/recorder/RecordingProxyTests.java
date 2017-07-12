package parrot.recorder;

import org.junit.Test;

public class RecordingProxyTests {
    @Test
    public void works(){
        RecordingProxy<Box> boxProxy = new RecordingProxy<>(new BoxImpl());
        Box box = boxProxy.getProxy(Box.class);
        box.getSize();
    }

    @Test
    public void works2(){
        Box box = RecordingProxy.getProxy(new BoxImpl(), Box.class);
        box.getSize();
    }

    interface Box{
        int getSize();
    }

    class BoxImpl implements Box{

        @Override
        public int getSize() {
            return 0;
        }
    }
}
