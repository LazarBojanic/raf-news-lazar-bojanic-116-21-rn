package rs.raf.rafnews.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ArticleSearchRequest {
    private Integer page;
    private Integer page_size;
    private String category_name;
    private Boolean trending;
    private String tag_name;
}
