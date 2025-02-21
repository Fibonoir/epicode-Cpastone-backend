package it.immobiliare365.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner initAdmin(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            String username = "admin";
            String email = "admin@example.com";
            String password = "password"; // Change this to a secure password

            if (adminRepository.findByUsername(username).isEmpty()) {
                Admin admin = new Admin();
                admin.setUsername(username);
                admin.setEmail(email);
                admin.setPassword(passwordEncoder.encode(password));

                adminRepository.save(admin);
                System.out.println("Admin user created successfully.");
            } else {
                System.out.println("Admin user already exists. No changes made.");
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
