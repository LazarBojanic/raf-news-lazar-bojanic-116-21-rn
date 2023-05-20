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
    private ServiceUserDto service_user;
    private ArticleDto article;
    private String body;
    private Timestamp time_published;

    public CommentDto(){
        this.id = -1;
        this.service_user = new ServiceUserDto();
        this.article = new ArticleDto();
        this.body = "";
        this.time_published = Timestamp.from(Instant.now());
    }
    public CommentDto(Comment comment, ServiceUserDto serviceUserDto, ArticleDto articleDto){
        this.id = comment.getId();
        this.service_user = serviceUserDto;
        this.article = articleDto;
        this.body = comment.getBody();
        this.time_published = comment.getTime_published();
    }
}
