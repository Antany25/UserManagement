package com.example.usermanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;  // Add this import
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "app_user")  // Use a different name, e.g., app_user
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the user")
    private Long id;

    @Column(unique = true)
    @Schema(description = "User's email address")
    private String email;

    @Schema(description = "User's password")
    private String password;

    @Schema(description = "User's role (e.g., USER, ADMIN)")
    private String role;

    @Schema(description = "User's IP address")
    private String ipAddress;

    @Schema(description = "User's country")
    private String country;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
