package rs.raf.rafnews.model;

import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Comment {
    private Integer id;
    private Integer service_user_id;
    private Integer article_id;
    private String content;
    private Timestamp time_published;
}
