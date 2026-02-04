package com.aidaml.cc.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aidaml.cc.demo.model.domain.User;
import com.aidaml.cc.demo.model.dto.AuthenticationDto;
import com.aidaml.cc.demo.exception.UserNotFoundException;
import com.aidaml.cc.demo.security.jwt.JwtTokenProvider;
import com.aidaml.cc.demo.service.AuthenticationService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    // 2. Login endpoint.
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody AuthenticationDto authDto) throws UserNotFoundException {

        User user = authenticationService.login(authDto);

        if (user != null) {
            String token = JwtTokenProvider.generateToken(authDto.getUsername());

            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

}
