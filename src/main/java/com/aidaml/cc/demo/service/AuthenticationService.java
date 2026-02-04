package com.aidaml.cc.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aidaml.cc.demo.model.domain.User;
import com.aidaml.cc.demo.model.dto.AuthenticationDto;
import com.aidaml.cc.demo.exception.BadCredentialsException;
import com.aidaml.cc.demo.exception.UserNotFoundException;
import com.aidaml.cc.demo.repository.UserRepository;
import com.aidaml.cc.demo.security.AESEncryptionComponent;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AESEncryptionComponent aesEncryptionComponent;

    public User login(AuthenticationDto authDto) throws UserNotFoundException, BadCredentialsException {
        System.out.println(authDto.toString());
        User user = userRepository.findByTaxId(authDto.getUsername());

        if (user == null) {
            throw new UserNotFoundException("User not found with tax ID: " + authDto.getUsername());
        }

        try {
            String plainPass = aesEncryptionComponent.cbcDecrypt(user.getPassword());
            System.out.println(plainPass);
            if (!plainPass.equals(authDto.getPassword())) {
                throw new IllegalArgumentException("The password entered is incorrect.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Decryption error: " + e.getMessage());
        }

        return user;
    }

}
