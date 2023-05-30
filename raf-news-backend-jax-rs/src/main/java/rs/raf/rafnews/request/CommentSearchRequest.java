package rs.raf.rafnews.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentSearchRequest {
    private Integer page;
    private Integer page_size;
}
