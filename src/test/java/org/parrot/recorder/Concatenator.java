package org.parrot.recorder;

import java.util.List;

public interface Concatenator {
    int concatLen(Object o1, Object o2);
    MyResult getMyResult(String s);
    MyResultList join(Object o1, String s2);
}
