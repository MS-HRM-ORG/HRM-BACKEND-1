package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.Role;
import com.msmavas.HRMS_Backend.models.User;
import com.msmavas.HRMS_Backend.models.UserRole;
import com.msmavas.HRMS_Backend.repositories.RoleRepository;
import com.msmavas.HRMS_Backend.repositories.UserRepository;
import com.msmavas.HRMS_Backend.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//service
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserRole findByUserIdAndRoleId(int userId, int roleId) {
        return userRoleRepository.findByUserUserIdAndRoleRoleId(userId, roleId);
    }

    @Override
    public UserRole createUserRole(int userId, int roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        
        return userRoleRepository.save(userRole);
    }

    @Override
    public void deleteUserRole(int userRoleId) {
        userRoleRepository.deleteById(userRoleId);
    }

    @Override
    public UserRole getUserRoleById(int userRoleId) {
        Optional<UserRole> userRoleOptional = userRoleRepository.findById(userRoleId);
        return userRoleOptional.orElse(null);
    }
    
}
