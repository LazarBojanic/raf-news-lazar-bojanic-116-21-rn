package rs.raf.rafnews.request;

import lombok.*;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.dto.ServiceUserDto;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class CommentRequest {
    private Integer id;
    private Integer service_user_id;
    private Integer article_id;
    private String body;
    private Timestamp time_published;
    public CommentRequest(){
        this.id = -1;
        this.service_user_id = -1;
        this.article_id =  -1;
        this.body = "";
        this.time_published = Timestamp.from(Instant.now());
    }
}
