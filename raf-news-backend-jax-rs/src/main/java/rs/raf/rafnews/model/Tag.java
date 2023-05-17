package rs.raf.rafnews.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Tag {
    private Integer id;
    private String name;
}
