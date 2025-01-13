package org.example.aeroportfx.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String username;

    private String password;

    public LoginDto(String username, String password) {
        this.username=username;
        this.password=password;
    }


}
