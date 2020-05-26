package com.example.ConsumerManagement.models.repositories;

import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
