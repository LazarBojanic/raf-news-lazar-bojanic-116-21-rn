package rs.raf.rafnews.model;

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
    private String is_enabled;
    private String first_name;
    private String last_name;
}
