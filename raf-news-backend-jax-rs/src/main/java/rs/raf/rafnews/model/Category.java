package rs.raf.rafnews.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Category {
    private Integer id;
    private String category_name;
    private String description;
    public Category(){
        this.id = -1;
        this.category_name = "";
        this.description = "";
    }
}
