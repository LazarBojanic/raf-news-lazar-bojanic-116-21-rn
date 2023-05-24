package rs.raf.rafnews.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleWithTagRequest {
    private Integer article_id;
    private String tag_name;
}
