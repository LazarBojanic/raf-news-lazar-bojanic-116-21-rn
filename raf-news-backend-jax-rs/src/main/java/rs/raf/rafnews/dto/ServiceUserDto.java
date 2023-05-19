package rs.raf.rafnews.dto;

import lombok.*;
import rs.raf.rafnews.model.ServiceUser;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ServiceUserDto {
    private Integer id;
    private String username;
    private String email;
    private String pass;
    private String user_role;
    private String is_enabled;
    private String first_name;
    private String last_name;

    public ServiceUserDto(){
        this.id = 0;
        this.username = "";
        this.email = "";
        this.pass = "";
        this.user_role = "";
        this.is_enabled = "";
        this.first_name = "";
        this.last_name = "";
    }
    public ServiceUserDto(ServiceUser serviceUser){
        this.id = serviceUser.getId();
        this.username = serviceUser.getUsername();
        this.email = serviceUser.getEmail();
        this.pass = serviceUser.getPass();
        this.user_role = serviceUser.getUser_role();
        this.is_enabled = serviceUser.getIs_enabled();
        this.first_name = serviceUser.getFirst_name();
        this.last_name = serviceUser.getLast_name();
    }
}
