package rw.ac.rca.spring_boot_template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.spring_boot_template.dtos.requests.TransactionRequest;
import rw.ac.rca.spring_boot_template.models.Transaction;
import rw.ac.rca.spring_boot_template.services.BankingService;

@RestController
@RequestMapping("/api/banking")
public class BankingController {

    @Autowired
    private BankingService bankingService;

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody TransactionRequest request) {
        Transaction transaction = bankingService.deposit(request.getCustomerId(), request.getAmount());
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody TransactionRequest request) {
        Transaction transaction = bankingService.withdraw(request.getCustomerId(), request.getAmount());
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransactionRequest request) {
        if (request.getDestinationAccountId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Transaction transaction = bankingService.transfer(request.getCustomerId(), request.getDestinationAccountId(), request.getAmount());
        return ResponseEntity.ok(transaction);
    }
}
