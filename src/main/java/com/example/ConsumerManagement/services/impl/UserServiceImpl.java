package com.example.ConsumerManagement.services.impl;

import com.example.ConsumerManagement.models.persistence.entities.User;
import com.example.ConsumerManagement.models.repositories.UserRepository;
import com.example.ConsumerManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findById(String username) {
        Optional<User> user = userRepository.findById(username);
        return user;
    }

    @Override
    public <S extends User> S save(S user) {
        return (S) userRepository.save(user);
    }

    @Override
    public boolean isExist(String username) {
        if( findById(username) == null) return  false;
        return true;
    }
}