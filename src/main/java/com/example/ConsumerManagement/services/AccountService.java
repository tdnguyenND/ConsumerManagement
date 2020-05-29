package com.example.ConsumerManagement.services;

import com.example.ConsumerManagement.models.persistence.entities.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AccountService {
    Optional<Account> findById (String username);
}
