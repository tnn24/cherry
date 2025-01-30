package com.tnn.component.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encryptPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean validatePassword(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}