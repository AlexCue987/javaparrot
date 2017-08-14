package org.parrot.typed;

@FunctionalInterface
public interface ObjectFromStringFactory {
    Object of(String value);
}
