package rs.raf.rafnews.dto;

import lombok.*;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.model.Tag;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ArticleDto {
    private Integer id;
    private ServiceUserDto service_user;
    private CategoryDto category;
    private String title;
    private String body;
    private Timestamp time_published;
    private Integer number_of_views;
    private List<ArticleWithTagDto> tag_list;
    public ArticleDto(){
        this.id = -1;
        this.service_user = new ServiceUserDto();
        this.category = new CategoryDto();
        this.title = "";
        this.body = "";
        this.time_published = Timestamp.from(Instant.now());
        this.number_of_views = 0;
        this.tag_list = new ArrayList<>();
    }

    public ArticleDto(Article article, ServiceUserDto serviceUserDto, CategoryDto categoryDto, List<ArticleWithTagDto> tag_list){
        this.id = article.getId();
        this.service_user = serviceUserDto;
        this.category = categoryDto;
        this.title = article.getTitle();
        this.body = article.getBody();
        this.time_published = article.getTime_published();
        this.number_of_views = article.getNumber_of_views();
        this.tag_list = tag_list;
    }
}