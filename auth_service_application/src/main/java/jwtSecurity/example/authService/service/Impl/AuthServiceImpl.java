package jwtSecurity.example.authService.service.Impl;

import jwtSecurity.example.authService.config.JwtTokenProvider;
import jwtSecurity.example.authService.dto.LoginDto;
import jwtSecurity.example.authService.dto.RegisterDto;
import jwtSecurity.example.authService.model.Role;
import jwtSecurity.example.authService.model.User;
import jwtSecurity.example.authService.repository.RoleRepository;
import jwtSecurity.example.authService.repository.UserRepository;
import jwtSecurity.example.authService.service.AuthService;
import jwtSecurity.example.authService.exception.DuplicateException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String login(LoginDto loginDto) {
        log.info("Attempting to authenticate user: {}", loginDto.getUsername());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Authentication successful for user: {}", loginDto.getUsername());
        return jwtTokenProvider.generateToken(authentication);
    }
    @Override
    public String register(RegisterDto registerDto){
        log.info("Registration request received for email: {}", registerDto.getEmail());
        User user= User.builder()
                .username(registerDto.getUsername())
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new DuplicateException("Вы уже были зарегистрированы");
        }

        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) { throw new RuntimeException("Role USER not found"); }
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        log.info("User registered successfully with email: {}", user.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                registerDto.getUsername(), registerDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public boolean checkUserExistsByEmail(String email) {
        log.info("Checking if user exists with email: {}", email);
        return userRepository.existsByEmail(email);
    }

}
