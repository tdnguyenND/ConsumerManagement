package com.example.ConsumerManagement.models.repositories;

import com.example.ConsumerManagement.models.persistence.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    User save (User user);
    Optional<User> findById(String username);
}
