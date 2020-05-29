package com.example.ConsumerManagement.controllers.transaction;

import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import com.example.ConsumerManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GetTransactionInfoController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transaction/{transactionId}")
    public Optional<Transaction> getInfo(@PathVariable int transactionId) throws Exception{
        return transactionService.findById(transactionId);
    }
}
