package ru.job4j.lombok;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Category {
    @Getter
    @NonNull
    private int id;
    @Getter
    @Setter
    private String name;
}
