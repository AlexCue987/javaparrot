package org.parrot.recorder;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class MyResultList {
    @NonNull
    private final List<MyResult> myResults;
}
