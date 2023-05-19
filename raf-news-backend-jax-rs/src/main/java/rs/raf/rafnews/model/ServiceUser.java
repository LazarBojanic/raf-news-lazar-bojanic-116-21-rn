package rs.raf.rafnews.model;

import lombok.*;

@Getter
@Setter
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
    public ServiceUser(){
        this.id = -1;
        this.username = "";
        this.email = "";
        this.user_role = "";
        this.is_enabled = "";
        this.first_name = "";
        this.last_name = "";
    }
}
