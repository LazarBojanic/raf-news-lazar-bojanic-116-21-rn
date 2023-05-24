package rs.raf.rafnews.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Tag {
    private Integer id;
    private String tag_name;

    public Tag(){
        this.id = -1;
        this.tag_name = "";
    }
}
