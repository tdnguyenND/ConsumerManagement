package com.example.ConsumerManagement.services;

import com.example.ConsumerManagement.models.persistence.entities.Transaction;

import java.util.Optional;

public interface TransactionService {
    <S extends Transaction> S save(S s);

    Optional<Transaction> findById(Integer integer);

    void deleteByFundId(int fundId);

    Iterable<Transaction> findAllByFundId(int fundId);

    boolean existById(Integer transactionId);
}
