package com.thunv.service.impl;

import com.thunv.common.SystemConstant;
import com.thunv.controller.output.login.SignupRequest;
import com.thunv.entity.RoleEntity;
import com.thunv.entity.UserEntity;
import com.thunv.repository.RoleRepository;
import com.thunv.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public UsersService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Transactional
    public void registerUser(SignupRequest signupRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signupRequest.getEmail());
        userEntity.setUsername(signupRequest.getUsername());
        userEntity.setPassword(encoder.encode(signupRequest.getPassword()));

        List<String> strRole = signupRequest.getRole();
        List<RoleEntity> roles = new ArrayList<>();
        if (strRole == null){
            RoleEntity roleEntity = roleRepository.findByCode(SystemConstant.ROLE_USER);
            roles.add(roleEntity);
        }else {
            strRole.forEach(role -> {
                switch (role){
                    case "admin":
                        RoleEntity adminRole = roleRepository.findByCode(SystemConstant.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    case "mod":
                        RoleEntity modRole = roleRepository.findByCode(SystemConstant.ROLE_MODERATOR);
                        roles.add(modRole);
                        break;
                    default:
                        RoleEntity userRole = roleRepository.findByCode(SystemConstant.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }
        userEntity.setRoles(roles);
        userRepository.save(userEntity);
    }
}
