package org.parrot.typed;

public interface Serializer {
    boolean canSerialize(Object object);
    TypedObject serialize(Object object);
}
