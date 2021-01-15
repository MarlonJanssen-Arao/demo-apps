package tech.arao.minibank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.arao.minibank.data.domain.Transaction;
import tech.arao.minibank.data.repository.TransactionRepository;
import tech.arao.minibank.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/bank/transactions")
public class TransactionRESTController {

    @Autowired
    private TransactionRepository repository;


    @GetMapping("/{id}")
    public Transaction get(@PathVariable(value = "id") Long transactionId) {
        return repository.findById(transactionId).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", transactionId));
    }
}
