package rs.raf.rafnews.dto;

import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CommentDto {
    private Integer id;
    private ServiceUserDto service_user;
    private ArticleDto article;
    private String body;
    private Timestamp time_published;
}
