package parrot;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class MethodSignature {
    @NonNull
    private final List<String> parameterTypes;
}
