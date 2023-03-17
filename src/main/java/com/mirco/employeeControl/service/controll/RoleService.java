package com.mirco.employeeControl.service.controll;

import com.mirco.employeeControl.model.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findById(int id);
}
