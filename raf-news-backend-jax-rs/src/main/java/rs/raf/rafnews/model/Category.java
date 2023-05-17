package rs.raf.rafnews.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Category {
    private Integer id;
    private String name;
    private String description;
}
