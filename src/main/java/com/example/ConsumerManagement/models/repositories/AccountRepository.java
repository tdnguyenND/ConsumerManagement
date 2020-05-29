package com.example.ConsumerManagement.models.repositories;

import com.example.ConsumerManagement.models.persistence.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account,String> {

    Optional<Account> findById (String username);
}
