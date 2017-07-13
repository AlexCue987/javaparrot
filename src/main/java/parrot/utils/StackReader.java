package parrot.utils;

import java.util.regex.Pattern;

public class StackReader {
    public static String getCallingFunctionName(int stepsUp){
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = trace[2 + stepsUp];
        String fullClassName = caller.getClassName();
        String[] tokens = fullClassName.split(Pattern.quote("."));
        String className = tokens[tokens.length - 1];
        return String.format("%s.%s", className, caller.getMethodName());
    }

    static String getSimpleClassName(String fullClassName){
        String[] tokens = fullClassName.split(Pattern.quote("."));
        return tokens[tokens.length - 1];
    }
}
