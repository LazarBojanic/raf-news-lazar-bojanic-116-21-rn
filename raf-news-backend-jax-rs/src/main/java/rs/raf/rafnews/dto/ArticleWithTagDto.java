package rs.raf.rafnews.dto;

import lombok.*;
import rs.raf.rafnews.model.Tag;
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ArticleWithTagDto {
    private Integer id;
    private Integer article_id;
    private Tag tag;
    public ArticleWithTagDto(){
        this.id = -1;
        this.article_id = -1;
        this.tag = new Tag();
    }
}
