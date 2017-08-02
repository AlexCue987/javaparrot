package org.parrot;
import java.util.List;
import java.util.Map;

public class NestedThing {
    private List<String> names;
    private Map<String, Object> attributes;

    public NestedThing(){}

    public NestedThing(List<String> names, Map<String, Object> attributes) {
        this.names = names;
        this.attributes = attributes;
    }

    public List<String> getNames() {
        return names;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "NestedThing{" +
                "\n names=" + names +
                ",\n attributes=" + attributes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NestedThing that = (NestedThing) o;

        if (!names.equals(that.names)) return false;
        return attributes.equals(that.attributes);
    }

    @Override
    public int hashCode() {
        int result = names.hashCode();
        result = 31 * result + attributes.hashCode();
        return result;
    }
}
