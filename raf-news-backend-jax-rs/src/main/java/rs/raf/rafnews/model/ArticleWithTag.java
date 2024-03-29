package rs.raf.rafnews.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ArticleWithTag {
    private Integer id;
    private Integer article_id;
    private Integer tag_id;
    public ArticleWithTag(){
        this.id = -1;
        this.article_id = -1;
        this.tag_id = -1;
    }
}
