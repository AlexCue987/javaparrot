package parrot.storage;

import parrot.InvocationInfo;

public interface InvocationRecorder {
    void save(InvocationInfo invocationInfo, Object result);
}
