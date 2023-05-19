package rs.raf.rafnews.dto;

import lombok.*;
import rs.raf.rafnews.model.Category;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class CategoryDto {
    private Integer id;
    private String category_name;
    private String description;

    public CategoryDto(){
        this.id = -1;
        this.category_name = "";
        this.description = "";
    }
    public CategoryDto(Category category){
        this.id = category.getId();
        this.category_name = category.getCategory_name();
        this.description = category.getDescription();
    }
}
