package rs.raf.rafnews.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RegisterRequest {
    private String username;
    private String email;
    private String pass;
    private String confirm_pass;
    private String first_name;
    private String last_name;
}
