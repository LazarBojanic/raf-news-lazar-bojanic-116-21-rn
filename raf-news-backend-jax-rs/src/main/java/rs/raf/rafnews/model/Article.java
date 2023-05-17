package rs.raf.rafnews.model;

import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Article {
    private Integer id;
    private Integer service_user_id;
    private Integer category_id;
    private String title;
    private String content;
    private Timestamp time_published;
    private Integer views;
}