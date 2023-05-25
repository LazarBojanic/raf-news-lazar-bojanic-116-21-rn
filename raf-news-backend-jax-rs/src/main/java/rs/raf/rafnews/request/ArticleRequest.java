package rs.raf.rafnews.request;

import lombok.*;
import rs.raf.rafnews.model.Tag;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ArticleRequest {
    private Integer id;
    private Integer service_user_id;
    private String category_name;
    private String title;
    private String body;
    private List<Tag> tag_list;
}
