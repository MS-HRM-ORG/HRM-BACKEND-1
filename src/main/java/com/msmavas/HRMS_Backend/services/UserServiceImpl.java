package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.Role;
import com.msmavas.HRMS_Backend.models.User;
import com.msmavas.HRMS_Backend.repositories.RoleRepository;
import com.msmavas.HRMS_Backend.repositories.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        // Ensure the user exists
        Optional<User> existingUser = userRepository.findById(user.getUserId());
        if (existingUser.isPresent()) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + user.getUserId());
        }
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public Optional<User> authenticateUser(String email, String password) {
        Optional<User> user =userRepository.findByEmailAndPasswordHash(email, password);
        if (user.isPresent() && user.get().getPasswordHash().equals(password)) {
            return user;
        } else {
            return Optional.empty();
        }
    }
}

