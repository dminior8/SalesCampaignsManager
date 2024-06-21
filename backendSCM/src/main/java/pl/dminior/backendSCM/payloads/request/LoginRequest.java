package pl.dminior.backendSCM.payloads.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
