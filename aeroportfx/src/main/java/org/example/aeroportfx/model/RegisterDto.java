package org.example.aeroportfx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDto {
    private String username;
    private String name;
    private String email;
    private String password;

    public RegisterDto() {}
    public RegisterDto(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername( String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
