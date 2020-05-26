package com.example.ConsumerManagement.services;

import com.example.ConsumerManagement.models.persistence.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(String s);

    <S extends User> S save(S s);

    boolean isExist(String username);
}
