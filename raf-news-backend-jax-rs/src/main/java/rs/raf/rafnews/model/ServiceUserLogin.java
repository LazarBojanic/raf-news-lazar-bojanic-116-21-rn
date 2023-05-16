package rs.raf.rafnews.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ServiceUserLogin {
    @JsonProperty("email")
    private String email;
    @JsonProperty("pass")
    private String pass;
}
