package rs.raf.rafnews.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ServiceUserRegister {
    private String username;
    private String email;
    private String pass;
    private String confirm_pass;
    private String first_name;
    private String last_name;
}
