package org.parrot;

import java.util.List;
import java.util.Map;

public class VeryNestedThing {
    private List<NestedThing> nestedThings;
    private Map<String, NestedThing> nestedThingMap;

    public VeryNestedThing(){}

    public VeryNestedThing(List<NestedThing> nestedThings, Map<String, NestedThing> nestedThingMap) {
        this.nestedThings = nestedThings;
        this.nestedThingMap = nestedThingMap;
    }

    public List<NestedThing> getNestedThings() {
        return nestedThings;
    }

    public Map<String, NestedThing> getNestedThingMap() {
        return nestedThingMap;
    }

    @Override
    public String toString() {
        return "VeryNestedThing{" +
                "nestedThings=" + nestedThings +
                ", nestedThingMap=" + nestedThingMap +
                '}';
    }
}
