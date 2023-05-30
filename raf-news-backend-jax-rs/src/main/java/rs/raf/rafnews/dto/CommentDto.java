package rs.raf.rafnews.dto;

import lombok.*;
import rs.raf.rafnews.model.Comment;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class CommentDto {
    private Integer id;
    private ArticleDto article;
    private String author;
    private String body;
    private Timestamp time_published;

    public CommentDto(){
        this.id = -1;
        this.article = new ArticleDto();
        this.author = "";
        this.body = "";
        this.time_published = Timestamp.from(Instant.now());
    }
    public CommentDto(Comment comment, ArticleDto articleDto){
        this.id = comment.getId();
        this.article = articleDto;
        this.author = comment.getAuthor();
        this.body = comment.getBody();
        this.time_published = comment.getTime_published();
    }
}
