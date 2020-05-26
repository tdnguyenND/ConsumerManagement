package com.example.ConsumerManagement.services.impl;

import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import com.example.ConsumerManagement.models.repositories.TransactionRepository;
import com.example.ConsumerManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public <S extends Transaction> S save(S s) {
        return null;
    }

    @Override
    public Optional<Transaction> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public void deleteByFundId(int fundId) {

    }

    @Override
    public Iterable<Transaction> findAllByFundId(int fundId) {
        return null;
    }
}
