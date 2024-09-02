package com.kartikeye.SpringSecurity.service;

import com.kartikeye.SpringSecurity.dao.UserRepository;
import com.kartikeye.SpringSecurity.model.User;
import com.kartikeye.SpringSecurity.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user  =  userRepository.findByUsername(username);
       if(user == null){
           System.out.println("User not found");
           throw new UsernameNotFoundException("User 404");
       }

        return new UserPrincipal(user);
    }
}
