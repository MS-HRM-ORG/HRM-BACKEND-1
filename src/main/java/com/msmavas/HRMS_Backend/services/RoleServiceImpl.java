package com.msmavas.HRMS_Backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msmavas.HRMS_Backend.models.Role;
import com.msmavas.HRMS_Backend.repositories.RoleRepository;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRoleById(int roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(int roleId) {
        roleRepository.deleteById(roleId);
    }
    
    @Override
    public Role updateRole(int roleId, Role role) {
        Optional<Role> existingRole = roleRepository.findById(roleId);
        if (existingRole.isPresent()) {
            // Check for unique role name
            Optional<Role> roleWithSameName = roleRepository.findByRoleName(role.getRoleName());
            if (roleWithSameName.isPresent() && roleWithSameName.get().getRoleId() != roleId) {
                throw new IllegalArgumentException("Role name already exists: " + role.getRoleName());
            }
            Role updatedRole = existingRole.get();
            updatedRole.setRoleName(role.getRoleName()); // Update other fields as necessary
            return roleRepository.save(updatedRole);
        } else {
            throw new IllegalArgumentException("Role not found with ID: " + roleId);
        
        }
    }

    @Override
    public Optional<Role> findRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
