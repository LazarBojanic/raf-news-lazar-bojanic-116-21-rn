package rs.raf.rafnews.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ArticleWithTagDto {
    private Integer id;
    private ArticleDto article;
    private TagDto tag;
}
