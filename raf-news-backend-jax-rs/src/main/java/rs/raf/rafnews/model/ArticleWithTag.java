package rs.raf.rafnews.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ArticleWithTag {
    private Integer id;
    private Integer article_id;
    private Integer tag_id;
}
