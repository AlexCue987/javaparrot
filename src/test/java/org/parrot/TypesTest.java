package org.parrot;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypesTest {
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
}
