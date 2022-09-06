package com.samueljavaspringboot.bankng_system.security;

import com.samueljavaspringboot.bankng_system.entity.Users;
import com.samueljavaspringboot.bankng_system.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private  UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userByUserName = usersRepository.findUserByUserName(username);
        if (userByUserName.equals(null)){throw  new UsernameNotFoundException("USER COULD NOT BE FOUND");}
        return new MyUserDetails(userByUserName);
    }
}
