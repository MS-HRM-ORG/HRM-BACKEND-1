package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.DTO.UserDTO;
import com.msmavas.HRMS_Backend.models.Role;
import com.msmavas.HRMS_Backend.models.User;
import com.msmavas.HRMS_Backend.repositories.RoleRepository;
import com.msmavas.HRMS_Backend.repositories.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    
    private static final int OTP_VALID_DURATION = 5;

    @Override
    public User saveUser(User user) {
        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getUserId());
        if (existingUser.isPresent()) {
            User savedUser = existingUser.get();
            if (user.getUsername() != null) {
                savedUser.setUsername(user.getUsername());
            }
            if (user.getEmail() != null) {
                savedUser.setEmail(user.getEmail());
            }
            if (user.getPasswordHash() != null && !user.getPasswordHash().isEmpty()) {
                savedUser.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
            }
            return userRepository.save(savedUser);
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

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setCreatedBy(user.getCreatedBy());
        dto.setStatus(user.getStatus());
        dto.setDeletedBy(user.getDeletedBy());
        return dto;
    }
    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public Optional<User> authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPasswordHash())) {
                return userOptional;
            }
        }
        return Optional.empty();
    }
    public void generateAndSendOtp(String email) {
        Random random = new Random();
        String otp = String.format("%06d", random.nextInt(1000000));

        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(OTP_VALID_DURATION);

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setOtp(otp);
            user.setOtpExpiryDate(expiryDate);
            userRepository.save(user);

            String subject = "Password Reset OTP";
            String text = "Your OTP for password reset is: " + otp;
            emailService.sendEmail(email, subject, text);
        }
    }

    public boolean verifyOtp(String email, String otp) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getOtp().equals(otp) && user.getOtpExpiryDate().isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    public void updatePassword(String email, String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPasswordHash(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }
    }
}

