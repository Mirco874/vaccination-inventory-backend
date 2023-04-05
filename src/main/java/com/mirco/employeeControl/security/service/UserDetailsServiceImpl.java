package com.mirco.employeeControl.security.service;

import com.mirco.employeeControl.model.repository.UserRepository;
import com.mirco.employeeControl.service.controll.RoleService;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;


    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.mirco.employeeControl.model.entity.User> user= userRepository.findByEmail( username );

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("email not registered");
        }

        List<GrantedAuthority> authorities = roleService.findById(user.get().getId())
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(authority -> log.info("ROLE: " + authority.getAuthority()))
                .collect(Collectors.toList());
        return new User(user.get().getEmail(), user.get().getPassword(), true , true, true, true, authorities);
    }
}
