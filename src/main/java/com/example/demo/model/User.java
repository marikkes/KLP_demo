package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Table(name = "user_info") // Matcher navnet på tabellen
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primærnøkkel med auto-increment

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;  // Enum for å begrense til USER eller ADMIN

    // Gettere og settere
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UserType getType() { return type; }
    public void setType(UserType type) { this.type = type; }
}
