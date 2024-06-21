package pl.dminior.backendSCM.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {
    private String token;
    private Long id;
    private String username;
}
