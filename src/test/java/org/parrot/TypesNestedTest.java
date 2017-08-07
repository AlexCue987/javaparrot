package org.parrot;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypesNestedTest {
    private final boolean active;
    private final int anInt;
    private final long aLong;
    private final double aDouble;
    @NonNull
    private final String aString;
    private final TypesNestedTest nestedTest;
}
