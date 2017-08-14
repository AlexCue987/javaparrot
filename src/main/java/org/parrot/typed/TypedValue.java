package org.parrot.typed;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TypedValue {
    @NonNull
    private final String className;
    private final String value;

    public static TypedValue of(Object object){
        return new TypedValue(object.getClass().getTypeName(), object.toString());
    }
}
