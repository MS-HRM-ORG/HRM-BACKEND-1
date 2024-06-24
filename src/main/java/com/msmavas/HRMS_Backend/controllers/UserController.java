package com.msmavas.HRMS_Backend.controllers;

import com.msmavas.HRMS_Backend.DTO.UserDTO;
import com.msmavas.HRMS_Backend.models.User;
import com.msmavas.HRMS_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully with ID: " + savedUser.getUserId());
    }


    @PostMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        user.setUserId(id);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPasswordHash();
        
        Optional<User> user = userService.authenticateUser(email, password);
        
        if (user.isPresent()) {
            System.out.println("Login successful!");
            return ResponseEntity.ok("Login successful!");
        } else {
            System.out.println("Login failed. Invalid email or password.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        userService.generateAndSendOtp(email);
        return ResponseEntity.ok("OTP has been sent to your email.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isOtpValid = userService.verifyOtp(email, otp);
        if (isOtpValid) {
            return ResponseEntity.ok("OTP is valid. You can now reset your password.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP.");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) {
        boolean isOtpValid = userService.verifyOtp(email, otp);
        if (isOtpValid) {
            userService.updatePassword(email, newPassword);
            return ResponseEntity.ok("Password has been successfully reset.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP.");
        }
    }

}
