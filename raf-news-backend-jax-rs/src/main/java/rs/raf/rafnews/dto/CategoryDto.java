package rs.raf.rafnews.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CategoryDto {
    private Integer id;
    private String name;
    private String description;
}
