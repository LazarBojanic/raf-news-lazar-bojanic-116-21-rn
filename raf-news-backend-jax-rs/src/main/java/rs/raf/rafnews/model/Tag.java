package rs.raf.rafnews.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Tag {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("tag_name")
    private String tag_name;

    public Tag(){
        this.id = -1;
        this.tag_name = "";
    }
}
