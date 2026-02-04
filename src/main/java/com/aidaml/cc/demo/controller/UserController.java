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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aidaml.cc.demo.exception.AccessDeniedException;
import com.aidaml.cc.demo.model.domain.User;
import com.aidaml.cc.demo.model.dto.UserCreationDto;
import com.aidaml.cc.demo.model.dto.UserUpdateDto;
import com.aidaml.cc.demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    // Temp for testing.
    @GetMapping("/list")
    public ResponseEntity<List<User>> get(HttpServletRequest request) {
        checkAuthorization(request);

        return ResponseEntity.ok(userService.list());
    }

    // 1.3. POST method to store a new user.
    @PostMapping("")
    public ResponseEntity<String> create(HttpServletRequest request, @Valid @RequestBody UserCreationDto userDto) {
        checkAuthorization(request);

        return ResponseEntity.ok(userService.create(userDto));
    }

    // 1.4 PATCH method to update an user.
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(HttpServletRequest request, @PathVariable UUID id, @RequestBody UserUpdateDto userDto) {
        if (!checkAuthorization(request)) {
            throw new AccessDeniedException();
        }

        return ResponseEntity.ok(userService.update(id, userDto));
    }

    // 1.6. DELETE method to remove an user by ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(HttpServletRequest request, @PathVariable UUID id) {
        checkAuthorization(request);

        return ResponseEntity.ok(userService.delete(id));
    }

    private Boolean checkAuthorization(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        Boolean authorized = true;

        if (username == null) {
            authorized = false;
        }

        if (!authorized) {
            throw new AccessDeniedException();
        }

        return authorized;
    }

}
