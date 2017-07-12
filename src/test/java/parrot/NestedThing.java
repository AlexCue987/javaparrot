package parrot;
import java.util.List;
import java.util.Map;

public class NestedThing {
    private final List<String> names;
    private final Map<String, Object> attributes;

    NestedThing(List<String> names, Map<String, Object> attributes) {
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
}
