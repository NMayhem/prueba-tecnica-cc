package com.aidaml.cc.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aidaml.cc.demo.model.domain.User;
import com.aidaml.cc.demo.model.dto.UserCreationDto;
import com.aidaml.cc.demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("")
public class UserController {

    @Autowired
    UserService userService;

    // Temp for testing.
    @GetMapping("/users/list")
    public ResponseEntity<List<User>> get(HttpServletRequest request) {
        return ResponseEntity.ok(userService.list());
    }

    // 1.3. POST method to store a new user.
    @PostMapping("/users")
    public ResponseEntity<String> create(HttpServletRequest request, @Valid @RequestBody UserCreationDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    // 1.6. DELETE method to remove a user by ID.
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> delete(HttpServletRequest request, @PathVariable UUID id) {
        return ResponseEntity.ok(userService.delete(id));
    }

}
