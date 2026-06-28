package com.cognizant.jwtauth.controller;

import com.cognizant.jwtauth.service.AuthenticationService;
import com.cognizant.jwtauth.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
 * Step 1: Authentication controller
 * Calls service to decode credentials and util to generate JWT
 */
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {

        String username = authenticationService.getUsernameFromHeader(authHeader);

        if (username == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid credentials");
            return error;
        }

        String token = jwtTokenUtil.generateToken(username);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
