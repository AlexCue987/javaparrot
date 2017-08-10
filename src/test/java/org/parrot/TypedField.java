package org.parrot;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypedField {
    @NonNull
    private final String type;
    @NonNull
    private final String name;
    private final boolean primitive;
    private final Object value;
}
