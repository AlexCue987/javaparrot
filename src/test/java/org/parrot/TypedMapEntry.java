package org.parrot;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypedMapEntry {
    @NonNull
    private final TypedInstance key;
    @NonNull
    private final TypedInstance value;
}
