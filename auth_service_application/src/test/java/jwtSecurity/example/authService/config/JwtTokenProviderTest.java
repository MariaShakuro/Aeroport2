package jwtSecurity.example.authService.config;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Collection;
import java.util.Arrays;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("JwtTokenProvider Unit Tests")
public class JwtTokenProviderTest {

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Authentication authentication;

    private String jwtSecret = "testsecretkeytestsecretkeytestsecretkeytestsecretkey";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtTokenProvider = new JwtTokenProvider();
        jwtTokenProvider.jwtSecret = jwtSecret;
    }

    @Test
    @DisplayName("Validate Token - Invalid Token")
    void testValidateTokenInvalid() {
        String token = "invalid.token";

        boolean isValid = jwtTokenProvider.validateToken(token);

        assertFalse(isValid);
    }

    @Test
    @DisplayName("Get Token from Request - Success")
    void testGetTokenFromRequest() {
        when(request.getHeader("Authorization")).thenReturn("Bearer valid.token");

        String token = jwtTokenProvider.getTokenFromRequest(request);

        assertEquals("valid.token", token);
    }

    @Test
    @DisplayName("Get Token from Request - No Bearer Token")
    void testGetTokenFromRequestNoBearer() {
        when(request.getHeader("Authorization")).thenReturn(null);

        String token = jwtTokenProvider.getTokenFromRequest(request);

        assertNull(token);
    }
}


