package com.microcommerce.userservice;

import com.microcommerce.userservice.data.entity.Role;
import com.microcommerce.userservice.data.entity.User;
import com.microcommerce.userservice.data.entity.UserRole;
import com.microcommerce.userservice.data.enums.Authority;
import com.microcommerce.userservice.repository.RoleRepository;
import com.microcommerce.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Slf4j
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByAuthority(Authority.ADMIN).isPresent() || roleRepository.findByAuthority(Authority.USER).isPresent()) return;

            Role adminRole = roleRepository.save(new Role(Authority.ADMIN.name()));
            Role userRole = roleRepository.save(new Role(Authority.USER.name()));
        };
    }
}
