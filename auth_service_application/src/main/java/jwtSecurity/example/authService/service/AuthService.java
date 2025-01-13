package jwtSecurity.example.authService.service;

import jwtSecurity.example.authService.dto.LoginDto;
import jwtSecurity.example.authService.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

    boolean checkUserExistsByEmail(String email);
}