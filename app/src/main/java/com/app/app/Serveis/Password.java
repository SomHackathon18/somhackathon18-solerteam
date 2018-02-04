package com.app.app.Serveis;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Password {

    private BCryptPasswordEncoder passwordEncoder;

    public Password() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public boolean matches(String password, String contrasenyaEncry) {

        return passwordEncoder.matches(password, contrasenyaEncry);
    }

    public String encode(String passsword) {
        return passwordEncoder.encode(passsword);
    }

}