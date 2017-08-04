package org.parrot.recorder;

public class MyResult {
    private int r;
    public MyResult(){}
    public MyResult(int r){this.r = r;}

    public int getR() {
        return r;
    }

    @Override
    public String toString() {
        return "MyResult{" +
                "r=" + r +
                '}';
    }
}
