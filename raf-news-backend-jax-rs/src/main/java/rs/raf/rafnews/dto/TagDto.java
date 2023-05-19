package rs.raf.rafnews.dto;

import lombok.*;
import rs.raf.rafnews.model.Tag;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class TagDto {
    private Integer id;
    private String tag_name;
    public TagDto(){
        this.id = -1;
        this.tag_name = "";
    }
    public TagDto(Tag tag){
        this.id = tag.getId();
        this.tag_name = tag.getTag_name();
    }
}
