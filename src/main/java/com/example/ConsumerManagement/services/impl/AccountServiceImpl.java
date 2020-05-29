package com.example.ConsumerManagement.services.impl;

import com.example.ConsumerManagement.models.persistence.entities.Account;
import com.example.ConsumerManagement.models.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements com.example.ConsumerManagement.services.AccountService {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public Optional<Account> findById(String username) {
        return accountRepository.findById(username);
    }
}
