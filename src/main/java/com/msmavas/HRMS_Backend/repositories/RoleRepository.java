package com.msmavas.HRMS_Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msmavas.HRMS_Backend.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}

