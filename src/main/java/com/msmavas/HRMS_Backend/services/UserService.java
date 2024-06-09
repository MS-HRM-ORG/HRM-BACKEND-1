package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
	User saveUser(User user);
    User updateUser(User user);
    void deleteUser(int userId);
    Optional<User> getUserById(int userId);
    List<User> getAllUsers();
    User findById(int id);
    Optional<User> authenticateUser(String email, String password);
}
