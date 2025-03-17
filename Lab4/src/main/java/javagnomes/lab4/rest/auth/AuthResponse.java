package javagnomes.lab4.rest.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthResponse implements Serializable {
    public String jwtToken;

    public AuthResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
