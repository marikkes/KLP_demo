package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.UserType;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        // Validerer type-verdi
        if (user.getType() == null || (!user.getType().equals(UserType.USER) && !user.getType().equals(UserType.ADMIN))) {
            return ResponseEntity.badRequest().body("Invalid type. Must be USER or ADMIN.");
        }

        userRepository.save(user);  // Lagrer bruker i databasen
        return ResponseEntity.ok("User created successfully");
    }
}
