package parrot.utils;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class CallerInfo {
    @NonNull
    private final String simpleClassName;
    @NonNull
    private final String methodName;
}
