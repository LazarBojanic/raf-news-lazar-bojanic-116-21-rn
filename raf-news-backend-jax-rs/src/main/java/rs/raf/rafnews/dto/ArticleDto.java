package rs.raf.rafnews.dto;

import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ArticleDto {
    private Integer id;
    private ServiceUserDto service_user;
    private CategoryDto category;
    private String title;
    private String content;
    private Timestamp time_published;
    private Integer views;
}