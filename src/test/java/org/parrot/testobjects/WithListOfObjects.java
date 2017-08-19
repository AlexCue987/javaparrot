package org.parrot.testobjects;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WithListOfObjects {
    @NonNull
    private final String name;
    private final int anInt;
    private final List<Object> objects;
}
