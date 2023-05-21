package rs.raf.rafnews.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSearchParams {
    private Integer page;
    private Integer page_size;
    private String category_name;
}
