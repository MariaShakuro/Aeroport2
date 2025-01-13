package jwtSecurity.example.authService.service;

import jwtSecurity.example.authService.model.Role;
import jwtSecurity.example.authService.model.User;
import jwtSecurity.example.authService.repository.RoleRepository;
import jwtSecurity.example.authService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role adminRole = Role.builder()
                    .name("ROLE_ADMIN")
                    .build();

            Role userRole = Role.builder()
                    .name("ROLE_USER")
                    .build();

            roleRepository.saveAll(Arrays.asList(adminRole, userRole));
        }


        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");

            User admin = User.builder()
                    .email("admin@example.com")
                    .name("Admin")
                    .password("admin123")
                    .username("admin")
                    .roles(new HashSet<>(Collections.singletonList(adminRole)))
                    .build();

            userRepository.save(admin);
        }
    }
}

