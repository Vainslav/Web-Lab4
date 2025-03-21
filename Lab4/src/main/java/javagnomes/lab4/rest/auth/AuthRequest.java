package javagnomes.lab4.rest.auth;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class AuthRequest {
    @NotNull(message = "USERNAME_MUST_NOT_BE_NULL_OR_EMPTY")
    @NotEmpty(message = "USERNAME_MUST_NOT_BE_NULL_OR_EMPTY")
    @Size(min = 4, message = "USERNAME_TOO_SHORT")
    @Size(max = 16, message = "USERNAME_TOO_LONG")
    private String username;
    @NotNull(message = "PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY")
    @NotEmpty(message = "PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY")
    @Size(min = 4, message = "PASSWORD_TOO_SHORT")
    private String password;
}