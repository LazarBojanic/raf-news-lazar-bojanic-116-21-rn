package rs.raf.rafnews.request;

import lombok.*;
import rs.raf.rafnews.model.Tag;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ArticleRequest {
    private Integer id;
    private Integer service_user_id;
    private String category_name;
    private String title;
    private String body;
    private Timestamp time_published;
    private Integer number_of_views;
    private List<Tag> tag_list;
}
