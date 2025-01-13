package jwtSecurity.example.authService.service.Impl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import jwtSecurity.example.authService.config.JwtTokenProvider;
import jwtSecurity.example.authService.dto.LoginDto;
import jwtSecurity.example.authService.dto.RegisterDto;
import jwtSecurity.example.authService.model.Role;
import jwtSecurity.example.authService.repository.RoleRepository;
import jwtSecurity.example.authService.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("AuthServiceImpl Integration Tests")
public class AuthServiceImplIntegrationTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    private LoginDto loginDto;
    private RegisterDto registerDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        loginDto = LoginDto.builder()
                .username("testuser")
                .password("password").build();

        registerDto = RegisterDto.builder()
                .email("testuser@example.com")
                .username("testuser")
                .password("password").build();


    }

    @Test
    @DisplayName("Login - Success")
    void testLogin() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));
        when(jwtTokenProvider.generateToken(any(Authentication.class))).thenReturn("mocked-token");

        String token = authService.login(loginDto);

        assertEquals("mocked-token", token);
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider, times(1)).generateToken(any(Authentication.class));
    }

    @Test
    @DisplayName("Check User Exists - Success")
    void testCheckUserExists() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        boolean exists = authService.checkUserExistsByEmail("testuser@example.com");

        assertTrue(exists);
        verify(userRepository, times(1)).existsByEmail(anyString());
    }
}



