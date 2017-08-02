package org.parrot;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class NestedThingInstanceCreator implements InstanceCreator<NestedThing> {
    private final List<String> names;
    private final Map<String, Object> attributes;

    NestedThingInstanceCreator(List<String> names, Map<String, Object> attributes) {
        this.names = names;
        this.attributes = attributes;
    }


    @Override
    public NestedThing createInstance(Type type) {
        return new NestedThing(names, attributes);
    }
}
