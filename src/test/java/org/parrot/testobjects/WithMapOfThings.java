package org.parrot.testobjects;

import lombok.*;

import java.util.Map;
import java.util.Set;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WithMapOfThings {
    @NonNull
    private final String name;
    private final int anInt;
    private final Map<Object, Object> things;
}
