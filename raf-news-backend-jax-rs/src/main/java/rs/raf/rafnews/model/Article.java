package rs.raf.rafnews.model;

import lombok.*;
import rs.raf.rafnews.dto.ArticleDto;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Article {
    private Integer id;
    private Integer service_user_id;
    private Integer category_id;
    private String title;
    private String body;
    private Timestamp time_published;
    private Integer number_of_views;
    public Article(){
        this.id = -1;
        this.service_user_id = -1;
        this.category_id = -1;
        this.title = "";
        this.body = "";
        this.time_published = Timestamp.from(Instant.now());
        this.number_of_views = -1;
    }
    public Article(ArticleDto articleDto){
        this.id = articleDto.getId();
        this.service_user_id = articleDto.getService_user().getId();
        this.category_id = articleDto.getCategory().getId();
        this.title = articleDto.getTitle();
        this.body = articleDto.getBody();
        this.time_published = articleDto.getTime_published();
        this.number_of_views = articleDto.getNumber_of_views();
    }
}