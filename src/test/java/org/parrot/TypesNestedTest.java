package org.parrot;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypesNestedTest {
    @NonNull
    private final boolean active;
    @NonNull
    private final int anInt;
    @NonNull
    private final long aLong;
    @NonNull
    private final double aDouble;
    @NonNull
    private final String aString;
    private final TypesNestedTest nestedTest;
}
