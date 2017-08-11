package org.parrot;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypedField {
    @NonNull
    private final String className;
    @NonNull
    private final String name;
    private final ValueType valueType;
    private final Object value;
}
