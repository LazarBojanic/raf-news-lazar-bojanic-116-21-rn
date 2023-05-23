package rs.raf.rafnews.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ServiceUserFromAdminRegister {
    private String username;
    private String email;
    private String pass;
    private String confirm_pass;
    private String user_role;
    private String first_name;
    private String last_name;
}
