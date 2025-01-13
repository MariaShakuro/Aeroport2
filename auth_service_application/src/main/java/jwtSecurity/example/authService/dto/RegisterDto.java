package jwtSecurity.example.authService.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {
    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(@NotBlank @Min(6) @Max(6) String password) {
        this.password = password;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }
}
