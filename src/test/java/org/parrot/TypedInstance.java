package org.parrot;

import lombok.*;

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
    private final ValueType valueType;
    @NonNull
    private final List<TypedField> fields;

    public static TypedInstance getPrimitive(TypedField typedField){
        return new TypedInstance(typedField.getClassName(), ValueType.PRIMITIVE, Collections.singletonList(typedField));
    }
}
