package rs.raf.rafnews.model;

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
public class Comment {
    private Integer id;
    private Integer article_id;
    private String author;
    private String body;
    private Timestamp time_published;
    public Comment(){
        this.id = -1;
        this.article_id =  -1;
        this.author = "";
        this.body = "";
        this.time_published = Timestamp.from(Instant.now());
    }
}
