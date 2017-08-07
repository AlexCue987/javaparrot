package org.parrot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class InstanceFactory {
    public Object of(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> map = gson.fromJson(json, type);
        System.out.println(map);
        return ofMap(map);
    }

    public Object ofMap(Map<String, Object> map){
        String type = map.get("className").toString();
        List<Map<String, Object>> fields = (List<Map<String, Object>>)map.get("fields");
        for(Map<String, Object> field: fields){
            System.out.println(field);
            String fieldType = field.get("type").toString();

        }
        return null;
    }
}
