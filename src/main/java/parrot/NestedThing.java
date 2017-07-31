package parrot;
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
}
