package org.parrot.typed;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypedField {
    @NonNull
    private final String fieldName;
    @NonNull
    private final TypedObject typedValue;
}
