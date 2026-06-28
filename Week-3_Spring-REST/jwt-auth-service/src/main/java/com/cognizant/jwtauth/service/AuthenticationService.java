package com.cognizant.jwtauth.service;

import org.springframework.stereotype.Service;

import java.util.Base64;

/*
 * Step 2: Read Authorization header and decode username and password
 */
@Service
public class AuthenticationService {

    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "pwd";

    public String getUsernameFromHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return null;
        }

        String base64Credentials = authHeader.substring("Basic ".length());
        String decoded = new String(Base64.getDecoder().decode(base64Credentials));
        String[] parts = decoded.split(":", 2);

        if (parts.length != 2) {
            return null;
        }

        String username = parts[0];
        String password = parts[1];

        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            return username;
        }

        return null;
    }
}
