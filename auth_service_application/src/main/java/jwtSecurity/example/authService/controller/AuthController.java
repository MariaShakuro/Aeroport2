package jwtSecurity.example.authService.controller;


import jakarta.validation.Valid;
import jwtSecurity.example.authService.config.JwtTokenProvider;
import jwtSecurity.example.authService.dto.AuthResponseDto;
import jwtSecurity.example.authService.dto.LoginDto;
import jwtSecurity.example.authService.dto.RegisterDto;
import jwtSecurity.example.authService.service.AuthService;
import jwtSecurity.example.authService.exception.DuplicateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        log.info("Login request received for user: {}", loginDto.getUsername());
            String token = authService.login(loginDto);
            AuthResponseDto authResponseDto = new AuthResponseDto();
            authResponseDto.setAccessToken(token);
            return new ResponseEntity<>(authResponseDto, HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto) {
        log.info("Registration request received for user: {}", registerDto.getEmail());
            String registeredUser = authService.register(registerDto);
            return new ResponseEntity<>("Поздравляем с успешной регистрацией", HttpStatus.CREATED);

    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        log.debug("Received token: {}", token);
        boolean isValid = jwtTokenProvider.validateToken(token.replace("Bearer ", "").trim());
        log.debug("Is token valid: {}", isValid);
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/check-user")
    public ResponseEntity<Boolean> checkUserExists(@RequestParam String email) {
        log.info("Check user exists request received for email: {}", email);
        boolean exists = authService.checkUserExistsByEmail(email);
        return ResponseEntity.ok(exists);
    }
}
