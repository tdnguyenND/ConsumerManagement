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
    public Optional<User> findById(String s) {
        return userRepository.findById(s);
    }

    @Override
    public <S extends User> S save(S s) {
        return null;
    }

    @Override
    public boolean isExist(String username) {
        return false;
    }
}
