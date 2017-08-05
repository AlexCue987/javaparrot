package org.parrot;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypedField {
    @NonNull
    private final String type;
    private final Object value;
}
