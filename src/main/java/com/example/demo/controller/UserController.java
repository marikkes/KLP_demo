package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.model.UserType;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        // Validerer type-verdi
        if (user.getType() == null || (!user.getType().equals(UserType.USER) && !user.getType().equals(UserType.ADMIN))) {
            return ResponseEntity.badRequest().body("Invalid type. Must be USER or ADMIN.");
        }

        userRepository.save(user);  // Lagrer bruker i databasen
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id); // Bruk Long her
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build(); // Returner 404 hvis ikke funnet
        }
    }

    @GetMapping
    public List<UserDTO> getUsers(@RequestParam(value = "type", required = false) String type) {
        List<User> users;

        // Hvis 'type' er spesifisert, filtrer etter type
        if (type != null) {
            users = userRepository.findByType(type);
        } else {
            // Hvis 'type' ikke er spesifisert, hent alle brukere
            users = userRepository.findAll();
        }

        // Returner en liste med nødvendige data (id, email, type)
        return users.stream()
                    .map(user -> new UserDTO(user.getId(), user.getEmail(), user.getType().toString()))
                    .collect(Collectors.toList());
    }
}
