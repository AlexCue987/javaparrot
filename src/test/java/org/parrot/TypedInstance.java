package org.parrot;

import lombok.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypedInstance {
    @NonNull
    private final String className;
    @NonNull
    private final List<TypedField> fields;

    public TypedInstance(TypedField typedField){
        this(typedField.getType(), Collections.singletonList(typedField));
    }
}
