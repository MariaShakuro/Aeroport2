package jwtSecurity.example.authService.config;

import static org.mockito.Mockito.*;

import java.io.IOException;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;


import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JwtAuthenticationFilter Unit Tests")
public class JwtAuthenticationFilterTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(jwtTokenProvider.getTokenFromRequest(any(HttpServletRequest.class))).thenReturn("valid-token");
        when(jwtTokenProvider.validateToken(anyString())).thenReturn(true);
        when(jwtTokenProvider.getUsername(anyString())).thenReturn("testuser");
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(new ArrayList<>());
    }

    @Test
    @DisplayName("doFilterInternal - Valid Token")
    void testDoFilterInternalValidToken() throws ServletException, IOException {
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(jwtTokenProvider, times(1)).getTokenFromRequest(request);
        verify(jwtTokenProvider, times(1)).validateToken("valid-token");
        verify(jwtTokenProvider, times(1)).getUsername("valid-token");
        verify(userDetailsService, times(1)).loadUserByUsername("testuser");
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    @DisplayName("doFilterInternal - Invalid Token")
    void testDoFilterInternalInvalidToken() throws ServletException, IOException {
        when(jwtTokenProvider.validateToken(anyString())).thenReturn(false);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(jwtTokenProvider, times(1)).getTokenFromRequest(request);
        verify(jwtTokenProvider, times(1)).validateToken("valid-token");
        verify(userDetailsService, never()).loadUserByUsername(anyString());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    @DisplayName("doFilterInternal - No Token")
    void testDoFilterInternalNoToken() throws ServletException, IOException {
        when(jwtTokenProvider.getTokenFromRequest(any(HttpServletRequest.class))).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(jwtTokenProvider, times(1)).getTokenFromRequest(request);
        verify(jwtTokenProvider, never()).validateToken(anyString());
        verify(userDetailsService, never()).loadUserByUsername(anyString());
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }
}
