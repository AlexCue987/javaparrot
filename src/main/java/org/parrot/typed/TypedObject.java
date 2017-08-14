package org.parrot.typed;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypedObject {
    @NonNull
    private final String className;
    @NonNull
    private final String persistingMethod;
    private final Object value;

    public static TypedObject getPrimitive(Object value){
        return new TypedObject(value.getClass().getTypeName(),
                PersistingMethod.VALUE.toString(),
                value.toString());
    }
}
