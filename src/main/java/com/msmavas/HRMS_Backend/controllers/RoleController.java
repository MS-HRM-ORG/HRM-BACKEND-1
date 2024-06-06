package com.msmavas.HRMS_Backend.controllers;

import com.msmavas.HRMS_Backend.DTO.RoleDTO;
import com.msmavas.HRMS_Backend.models.Role;
import com.msmavas.HRMS_Backend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        List<RoleDTO> roleDTOs = roles.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(roleDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable int id) {
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(value -> ResponseEntity.ok(convertToDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        Role createdRole = roleService.saveRole(role);
        return ResponseEntity.ok(convertToDTO(createdRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        role.setRoleId(id);
        Role updatedRole = roleService.saveRole(role);
        return ResponseEntity.ok(convertToDTO(updatedRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    private RoleDTO convertToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(role.getRoleId());
        roleDTO.setRoleName(role.getRoleName());
        return roleDTO;
    }

    private Role convertToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        return role;
    }
}
