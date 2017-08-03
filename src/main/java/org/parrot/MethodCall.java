package org.parrot;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class MethodCall {
    @NonNull
    private final List<MethodCallParameter> parameters;
    private final Object result;
}
