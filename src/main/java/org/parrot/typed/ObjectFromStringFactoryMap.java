package org.parrot.typed;

import java.util.HashMap;
import java.util.Map;

public class ObjectFromStringFactoryMap {
    public static Map<String, ObjectFromStringFactory> getFactoryMap(){
        Map<String, ObjectFromStringFactory> ret = new HashMap<>();
        ret.put("java.lang.Short", Short::valueOf);
        ret.put("java.lang.Integer", Integer::valueOf);
        ret.put("java.lang.Long", Long::valueOf);
        ret.put("java.lang.Boolean", Boolean::valueOf);
        ret.put("java.lang.Float", Float::valueOf);
        ret.put("java.lang.Double", Double::valueOf);
        ret.put("java.lang.Character", s -> s.charAt(0));
        ret.put("java.lang.String", t -> t);
        return ret;
    }

    public static String getClassName(Object object) {
        return object.getClass().getTypeName();
    }
}
