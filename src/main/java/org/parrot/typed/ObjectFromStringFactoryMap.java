package org.parrot.typed;

import java.util.HashMap;
import java.util.Map;

public class ObjectFromStringFactoryMap {
    public static Map<String, ObjectFromStringFactory> getFactoryMap(){
        Map<String, ObjectFromStringFactory> ret = new HashMap<>();
        ret.put("java.lang.Integer", Integer::valueOf);
        ret.put("java.lang.Long", Long::valueOf);
        ret.put("java.lang.String", t -> t);
        return ret;
    }

    public static String getClassName(Object object) {
        return object.getClass().getTypeName();
    }
}
