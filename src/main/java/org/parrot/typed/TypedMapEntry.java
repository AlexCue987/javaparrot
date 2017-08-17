package org.parrot.typed;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypedMapEntry {
    @NonNull
    private final TypedObject key;
    @NonNull
    private final TypedObject value;
}
