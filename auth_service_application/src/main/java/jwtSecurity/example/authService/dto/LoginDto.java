package jwtSecurity.example.authService.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    @NotBlank
    private String username;
    @NotBlank
    @Min(6)
    @Max(6)
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Min(6) @Max(6) String password) {
        this.password = password;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }
}
