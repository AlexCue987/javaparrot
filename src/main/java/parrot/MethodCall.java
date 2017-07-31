package parrot;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class MethodCall {
    @NonNull
    private final List<String> parameterTypes;
    @NonNull
    private final List<TypedValue> passedValues;
    @NonNull
    private final TypedValue result;
}
