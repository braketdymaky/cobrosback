package com.tins.cobros.security.service;

import com.tins.cobros.security.model.User;
import com.tins.cobros.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> getByName(String name){
        return userRepository.findByName(name);
    }

    public boolean existsUser(String name){
        return userRepository.existsByName(name);
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
