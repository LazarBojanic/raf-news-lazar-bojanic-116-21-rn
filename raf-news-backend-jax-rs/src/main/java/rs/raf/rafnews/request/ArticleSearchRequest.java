package rs.raf.rafnews.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSearchRequest {
    private Integer page;
    private Integer page_size;
    private String category_name;
}
