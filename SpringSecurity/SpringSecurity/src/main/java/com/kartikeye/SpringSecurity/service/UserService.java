package com.kartikeye.SpringSecurity.service;

import com.kartikeye.SpringSecurity.dao.UserRepository;
import com.kartikeye.SpringSecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
    }
}
