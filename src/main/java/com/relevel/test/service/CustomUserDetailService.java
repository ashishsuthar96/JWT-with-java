package com.relevel.test.service;

import com.relevel.test.Repository.UserRepo;
import com.relevel.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     User user = this.userRepo.findByEmail(username).get();
        return new org.springframework.security.core.userdetails
                .User(user.getEmail(),user.getPassword(), new ArrayList<>());
    }
}
