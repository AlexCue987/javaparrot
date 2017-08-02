package org.parrot.storage;

import org.parrot.InvocationInfo;

public interface InvocationRecorder {
    void save(InvocationInfo invocationInfo, Object result);
}
