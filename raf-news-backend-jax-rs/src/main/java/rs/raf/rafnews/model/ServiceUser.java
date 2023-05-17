package rs.raf.rafnews.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ServiceUser {
    private Integer id;
    private String username;
    private String email;
    private String pass;
    private String user_role;
    private String enabled;
    private String first_name;
    private String last_name;
}
