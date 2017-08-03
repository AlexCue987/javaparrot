package org.parrot;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class MethodCallParameter {
    @NonNull
    private final String declaredType;
    @NonNull
    private final Object submittedValue;
}
