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
    private Integer article_id;
    private String author;
    private String body;
    public CommentRequest(){
        this.article_id =  -1;
        this.author = "";
        this.body = "";
    }
}
