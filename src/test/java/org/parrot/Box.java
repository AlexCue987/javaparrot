package org.parrot;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Box {
    @NonNull
    private final String name;
    @NonNull
    private final List<Item> items;
    @NonNull
    private final List<Box> boxes;
}
