package com.mirco.employeeControl.service.controll;

import com.mirco.employeeControl.model.entity.Role;
import com.mirco.employeeControl.model.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolServiceImplementation implements RoleService {
    private final RoleRepository repository;

    @Autowired
    public RolServiceImplementation(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Role> findById(int id) {
        return this.repository.findById(id);
    }

}
