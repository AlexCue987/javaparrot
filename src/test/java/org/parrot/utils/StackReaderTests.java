package org.parrot.utils;

import org.junit.Assert;
import org.junit.Test;

public class StackReaderTests {
    @Test
    public void getCallingFunctionName(){
        int stepsUp = 0;
        String actual = StackReader.getCallingFunctionName(stepsUp);
        String expected = "StackReaderTests.getCallingFunctionName";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCallingFunctionName_handlesInnerClass(){
        String actual = new InnerClass().run();
        String expected = "StackReaderTests$InnerClass.run";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void worksInConstructor(){
        WithLoggedConstructor o = new WithLoggedConstructor();
        String expected = "StackReaderTests$WithLoggedConstructor.<init>";
        Assert.assertEquals(expected, o.getConstructorName());
    }

    @Test
    public void getSimpleClassName_works(){
        String fullCalssName = "solar.system.Mars";
        Assert.assertEquals("Mars", StackReader.getSimpleClassName(fullCalssName));
    }

    @Test
    public void getCallerInfo_works(){
        CallerInfo expected = new CallerInfo("StackReaderTests", "getCallerInfo_works");
        int stepsUp = 0;
        CallerInfo actual = StackReader.getCallerInfo(stepsUp);
        Assert.assertEquals(expected, actual);
    }

    private class InnerClass{
        private String run(){
            int stepsUp = 0;
            return StackReader.getCallingFunctionName(stepsUp);
        }
    }

    private class WithLoggedConstructor{
        private final String constructorName;
        private WithLoggedConstructor(){
            int stepsUp = 0;
            constructorName = StackReader.getCallingFunctionName(stepsUp);
        }

        public String getConstructorName() {
            return constructorName;
        }
    }
}
