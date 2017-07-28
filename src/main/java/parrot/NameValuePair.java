package parrot;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class NameValuePair {
    @NonNull
    private final String name;

    @NonNull
    private final Object value;

    @NonNull
    private final String typeName;
}
