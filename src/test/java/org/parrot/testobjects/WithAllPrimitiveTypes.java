package org.parrot.testobjects;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WithAllPrimitiveTypes {
    @NonNull
    private final boolean aBoolean;
    @NonNull
    private final char aChar;
    @NonNull
    private final short aShort;
    @NonNull
    private final int anInt;
    @NonNull
    private final long aLong;
    @NonNull
    private  final float aFloat;
    @NonNull
    private final double aDouble;
}
