package org.parrot;

import java.lang.reflect.Field;
import java.util.*;

public enum ValueType {
    PRIMITIVE{
        @Override
        public Object getValue(Field field, Object instance, Map<String, Object> fieldValue, InstanceFactory instanceFactory) {
            String fieldType = fieldValue.get("className").toString();
            Object value = fieldValue.get("value");
            String valueStr = value.toString();
            String name = fieldType.equals("java.lang.String") ? "STRING" : fieldType.toUpperCase();
            PrimitiveType primitiveType = PrimitiveType.valueOf(name);
            return primitiveType.of(valueStr);
        }
    },

    LIST{
        @Override
        public Object getValue(Field field, Object instance, Map<String, Object> fieldValue, InstanceFactory instanceFactory) {
            Object value = fieldValue.get("value");
            if(value == null){
                return null;
            }
            List list = (List)value;
            List ret = new ArrayList(list.size());
            for(int i=0; i<list.size(); i++){
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>)list.get(i);
                Object o = instanceFactory.ofMap(map);
                ret.add(o);
            }
            return ret;
        }
    },

    SET{
        @Override
        public Object getValue(Field field, Object instance, Map<String, Object> fieldValue, InstanceFactory instanceFactory) {
            Object value = fieldValue.get("value");
            if(value == null){
                return null;
            }
            List list = (List)value;
            Set ret = new HashSet(list.size());
            for(int i=0; i<list.size(); i++){
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>)list.get(i);
                Object o = instanceFactory.ofMap(map);
                ret.add(o);
            }
            return ret;
        }
    },

    MAP{
        @Override
        public Object getValue(Field field, Object instance, Map<String, Object> fieldValue, InstanceFactory instanceFactory) {
            Object typedValue = fieldValue.get("value");
            if(typedValue == null){
                return null;
            }
            Map<Object, Object> ret = new HashMap<>();
            List list = (List)typedValue;
            for(int i=0; i<list.size(); i++){
                @SuppressWarnings("unchecked")
                Map<String, Object> typedEntry = (Map<String, Object>)list.get(i);
                @SuppressWarnings("unchecked")
                Map<String, Object> entryKey = (Map<String, Object>)(typedEntry.get("key"));
                @SuppressWarnings("unchecked")
                Map<String, Object> entryValue = (Map<String, Object>)(typedEntry.get("value"));
                Object key = instanceFactory.ofMap((Map<String, Object>) entryKey);
                Object value = instanceFactory.ofMap((Map<String, Object>) entryValue);
                ret.put(key, value);
            }
            return ret;
        }
    },

    INSTANCE{
        @Override
        public Object getValue(Field field, Object instance, Map<String, Object> fieldValue, InstanceFactory instanceFactory) {
            Object value = fieldValue.get("value");
            if(value == null){
                return null;
            }
            Map<String, Object> typedValueAsMap = (Map<String, Object>) value;
            return typedValueAsMap==null ? null : instanceFactory.ofMap(typedValueAsMap);
        }
    };

    public abstract Object getValue(Field field, Object instance, Map<String, Object> fieldValue, InstanceFactory instanceFactory);
}
