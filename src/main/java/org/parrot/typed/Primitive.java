package org.parrot.typed;

import java.util.HashMap;
import java.util.Map;

public enum Primitive {

    INT {
        @Override
        public Object of(String value) {
            return Integer.valueOf(value);
        }
    };

    public abstract Object of(String value);

    public static boolean isPrimitive(String className){
        return mappings.containsKey(className);
    }

    private final static Map<String, Primitive> mappings = getMappings();

    private final static Map<String, Primitive> getMappings(){
        Map<String, Primitive> ret = new HashMap<>();
        ret.put("int", INT);
        ret.put("java.lang.Integer", INT);
        return ret;
    }
}
