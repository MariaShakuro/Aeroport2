package jwtSecurity.example.authService.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DisplayName("SecurityConfig Unit Tests")
public class SecurityConfigTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Password Encoder Bean Creation")
    void testPasswordEncoderBean() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
        assert(passwordEncoder instanceof BCryptPasswordEncoder);
    }

    @Test
    @DisplayName("Authentication Manager Bean Creation")
    void testAuthenticationManagerBean() throws Exception {
        AuthenticationConfiguration configuration = mock(AuthenticationConfiguration.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        when(configuration.getAuthenticationManager()).thenReturn(authenticationManager);

        AuthenticationManager result = securityConfig.authenticationManager(configuration);
        assertNotNull(result);
        assert(result == authenticationManager);
    }

    @Test
    @DisplayName("JWT Authentication Filter Bean Creation")
    void testJwtAuthenticationFilterBean() {
        JwtAuthenticationFilter jwtAuthenticationFilter = securityConfig.jwtAuthenticationFilter(jwtTokenProvider);
        assertNotNull(jwtAuthenticationFilter);
    }
}

