package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (user.getType() == null
                || (!user.getType().equals(UserType.USER) && !user.getType().equals(UserType.ADMIN))) {
            return ResponseEntity.badRequest().body("Invalid type. Must be USER or ADMIN.");
        }

        userRepository.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<UserDTO> getUsers(@RequestParam(value = "type", required = false) String type) {
        List<User> users;

        if (type != null) {
            try {
                UserType userType = UserType.valueOf(type.toUpperCase());
                users = userRepository.findByType(userType);
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user type: " + type);
            }
        } else {
            users = userRepository.findAll();
        }

        return users.stream()
                .map(user -> new UserDTO(user.getId(), user.getEmail(), user.getType().toString()))
                .collect(Collectors.toList());
    }
}
