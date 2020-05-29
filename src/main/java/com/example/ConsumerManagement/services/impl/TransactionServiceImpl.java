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
        return transactionRepository.save(s);
    }

    @Override
    public Optional<Transaction> findById(Integer integer) {
        return transactionRepository.findById(integer);
    }

    @Override
    public void deleteByFundId(int fundId) {
        transactionRepository.deleteById(fundId);
    }

    @Override
    public Iterable<Transaction> findAllByFundId(int fundId) {
        return transactionRepository.findAllByFundId(fundId);
    }
}
