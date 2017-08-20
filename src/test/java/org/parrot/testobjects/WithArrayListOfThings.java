package org.parrot.testobjects;

import lombok.*;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WithArrayListOfThings {
    @NonNull
    private final String name;
    private final int anInt;
    private final ArrayList<Thing> things;
}
