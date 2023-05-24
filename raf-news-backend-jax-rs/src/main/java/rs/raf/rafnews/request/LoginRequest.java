package rs.raf.rafnews.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginRequest {
    private String email;
    private String pass;
}
