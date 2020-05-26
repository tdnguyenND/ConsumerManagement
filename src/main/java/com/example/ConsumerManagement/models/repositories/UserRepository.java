package com.example.ConsumerManagement.models.repositories;

import com.example.ConsumerManagement.models.persistence.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
