package org.parrot.typed;

public interface Deserializer {
    Object deserialize(String className, Object object);
}
