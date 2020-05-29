package com.example.ConsumerManagement.controllers.transaction;

import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import com.example.ConsumerManagement.services.TransactionService;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CreateTransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/transaction", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@ModelAttribute("transaction") Transaction transaction, HttpServletResponse res){
        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();

        transaction.setDateOfCreation(dateOfCreation);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transaction));
    }
}
