package jwtSecurity.example.authService.config;


import java.util.Optional;
import java.util.Set;


import jwtSecurity.example.authService.model.Role;
import jwtSecurity.example.authService.model.User;
import jwtSecurity.example.authService.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.junit.jupiter.api.DisplayName;

import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("CustomUserDetailsService Unit Tests")
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .username("testuser")
                .password("password123")
                .build();
        Role role = Role.builder()
                .name("ROLE_USER")
                .build();
        user.setRoles(Set.of(role));
    }

    @Test
    @DisplayName("Load User By Username - Success")
    void testLoadUserByUsernameSuccess() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("testuser");

        assertEquals("testuser", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
    }

    @Test
    @DisplayName("Load User By Username - User Not Found")
    void testLoadUserByUsernameUserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("unknownuser");
        });
    }
}
