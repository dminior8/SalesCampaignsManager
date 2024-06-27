package pl.dminior.backendSCM.payloads.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.dminior.backendSCM.model.EnumRole;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private UUID id;
    private String username;
    private EnumRole role;
    private String password = "";

    public JwtResponse(String accessToken, UUID id, String username, EnumRole role) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.role = role;
    }
}