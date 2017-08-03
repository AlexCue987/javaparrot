package org.parrot.recorder;

import java.util.ArrayList;
import java.util.List;

public class ConcatenatorImpl implements Concatenator {

    @Override
    public int concatLen(Object o1, Object o2) {
        String s = o1.toString() + o2.toString();
        return s.length();
    }

    @Override
    public MyResult getMyResult(String s) {
        return new MyResult(s.length());
    }

    @Override
    public MyResultList join(Object o1, String s2) {
        List<MyResult> ret = new ArrayList<>(2);
        ret.add(new MyResult(o1.toString().length()));
        ret.add(new MyResult(s2.length()));
        ret.add(new MyResult(1234));
        return new MyResultList(ret);
    }
}
