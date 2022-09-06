package com.samueljavaspringboot.bankng_system.service;

import com.samueljavaspringboot.bankng_system.UserDto;
import com.samueljavaspringboot.bankng_system.entity.Role;
import com.samueljavaspringboot.bankng_system.entity.Roles;
import com.samueljavaspringboot.bankng_system.entity.Users;
import com.samueljavaspringboot.bankng_system.repos.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImp implements UserService{
    private final UsersRepository usersRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImp(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void createUser(UserDto userDto) {
        Users user = new Users();
        Roles roles = new Roles();
        roles.setName(Role.ROLE_USER.name());
        Set<Roles> roleSet = new HashSet<>();
        roleSet.add(roles);



        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setUserName(userDto.username());
        user.setRoles(roleSet);
        user.setEnabled(true);
        usersRepository.save(user);


    }
}
