package org.parrot.typed;

public interface Persister {
    boolean canPersist(Object object);
    TypedObject getTyped(Object object);
    Object getUntyped(TypedObject typedObject);
}
