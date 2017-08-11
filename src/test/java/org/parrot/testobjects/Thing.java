package org.parrot.testobjects;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Thing {
    @NonNull
    private final String name;
    private final int size;
}
