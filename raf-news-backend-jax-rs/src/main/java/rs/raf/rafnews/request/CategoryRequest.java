package rs.raf.rafnews.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryRequest {
    private Integer id;
    private String category_name;
    private String description;
}
