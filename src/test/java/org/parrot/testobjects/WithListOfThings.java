package org.parrot.testobjects;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WithListOfThings {
    @NonNull
    private final String name;
    private final int anInt;
    private final List<Thing> things;
}
