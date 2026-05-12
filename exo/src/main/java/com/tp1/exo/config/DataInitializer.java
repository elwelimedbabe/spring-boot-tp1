package com.tp1.exo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tp1.exo.entity.Role;
import com.tp1.exo.entity.User;
import com.tp1.exo.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            // إنشاء المدير إذا لم يكن موجودًا
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.addRole(Role.ROLE_ADMIN);
                userRepository.save(admin);
            }

            // إنشاء المستخدم العادي إذا لم يكن موجودًا
            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.addRole(Role.ROLE_USER);
                userRepository.save(user);
            }
        };
    }
}