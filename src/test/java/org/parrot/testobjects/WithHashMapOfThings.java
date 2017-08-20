package org.parrot.testobjects;

import lombok.*;

import java.util.HashMap;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WithHashMapOfThings {
    @NonNull
    private final String name;
    private final int anInt;
    private final HashMap<Object, Object> things;
}
