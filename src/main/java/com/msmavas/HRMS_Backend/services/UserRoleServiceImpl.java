package com.msmavas.HRMS_Backend.services;

import com.msmavas.HRMS_Backend.models.Role;
import com.msmavas.HRMS_Backend.models.User;
import com.msmavas.HRMS_Backend.models.UserRole;
import com.msmavas.HRMS_Backend.repositories.RoleRepository;
import com.msmavas.HRMS_Backend.repositories.UserRepository;
import com.msmavas.HRMS_Backend.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole getUserRoleById(int userRoleId) {
        Optional<UserRole> userRoleOptional = userRoleRepository.findById(userRoleId);
        return userRoleOptional.orElse(null);
    }

    @Override
    public UserRole createUserRole(int userId, int roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        String username = user.getUsername(); // Get username directly from user object
        String roleName = role.getRoleName(); // Get roleName directly from role object

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setUsername(username);
        userRole.setRoleName(roleName);

        return userRoleRepository.save(userRole);
    }
    @Override
    public void deleteUserRole(int userRoleId) {
        userRoleRepository.deleteById(userRoleId);
    }

    @Override
    public UserRole updateUserRole(int userRoleId, int userId, int roleId) {
        UserRole existingUserRole = userRoleRepository.findById(userRoleId)
                .orElseThrow(() -> new RuntimeException("User role not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        existingUserRole.setUser(user);
        existingUserRole.setRole(role);

        return userRoleRepository.save(existingUserRole);
    }
}
