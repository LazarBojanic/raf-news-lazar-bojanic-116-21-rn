package rs.raf.rafnews.model;

import lombok.*;
import rs.raf.rafnews.dto.TagDto;

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
    public Tag(TagDto tagDto){
        this.id = tagDto.getId();
        this.tag_name = tagDto.getTag_name();
    }
}
