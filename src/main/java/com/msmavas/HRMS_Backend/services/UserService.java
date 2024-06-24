package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.DTO.UserDTO;
import com.msmavas.HRMS_Backend.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
	User saveUser(User user);
    Optional<User> authenticateUser(String email, String password);

    User updateUser(User user);
    void deleteUser(int userId);
    Optional<User> getUserById(int userId);
    List<UserDTO> getAllUsers();
    User findById(int id);
    void generateAndSendOtp(String email);

    boolean verifyOtp(String email, String otp);

    void updatePassword(String email, String newPassword);
    
}
