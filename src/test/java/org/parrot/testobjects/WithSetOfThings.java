package org.parrot.testobjects;

import lombok.*;

import java.util.Set;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WithSetOfThings {
    @NonNull
    private final String name;
    private final int anInt;
    private final Set<Thing> things;
}
