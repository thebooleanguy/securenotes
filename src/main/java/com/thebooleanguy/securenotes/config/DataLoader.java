package com.thebooleanguy.securenotes.config;

import com.thebooleanguy.securenotes.model.Role;
import com.thebooleanguy.securenotes.model.User;
import com.thebooleanguy.securenotes.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setRole(Role.valueOf("ADMIN"));

            userRepository.save(user);
            System.out.println("✅ Test user created: admin / admin123");
        }
    }
}
