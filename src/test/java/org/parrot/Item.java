package org.parrot;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Item {
    @NonNull
    private final String itemName;
}
