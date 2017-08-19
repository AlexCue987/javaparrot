package org.parrot.testobjects;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WithNestedObject {
    private final boolean active;
    private final int anInt;
    private final long aLong;
    private final double aDouble;
    @NonNull
    private final String aString;
    private final WithNestedObject nestedObject;
}
