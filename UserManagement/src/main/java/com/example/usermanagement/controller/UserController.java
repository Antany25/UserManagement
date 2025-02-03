package com.example.usermanagement.controller;

import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "APIs for user registration and management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Root endpoint for testing
    @GetMapping("/")
    @Operation(summary = "Test Root Endpoint")
    public String home() {
        return "Welcome to the User Management API!";
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null); // Bad Request
        }
    }

    @PostMapping("/validate")
    @Operation(summary = "Validate user credentials")
    public ResponseEntity<Boolean> validateUser(@RequestParam String email, @RequestParam String password) {
        try {
            Boolean isValid = userService.validateUser(email, password);
            if (isValid == null) {
                return ResponseEntity.status(404).body(false); // Not Found
            }
            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(false); // Bad Request
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all registered users (Admin Only)")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users == null || users.isEmpty()) {
                return ResponseEntity.status(404).body(null); // Not Found
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a user by email (Admin Only)")
    public ResponseEntity<String> deleteUser(@RequestParam String email) {
        try {
            userService.deleteUser(email);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("User not found"); // Not Found
        }
    }
}
