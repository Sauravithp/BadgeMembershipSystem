package miu.edu.badgesystem.controller;


import miu.edu.badgesystem.dto.request.TransactionRequestDTO;
import miu.edu.badgesystem.dto.response.TransactionDTO;
import miu.edu.badgesystem.model.Transaction;
import miu.edu.badgesystem.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping
    private ResponseEntity<TransactionDTO> save(@RequestBody TransactionRequestDTO transactionDto) {

        Transaction savedTransaction = transactionService.saveTransaction(transactionDto);
        System.out.println(savedTransaction);
        return ResponseEntity.ok(mapper.map(savedTransaction, TransactionDTO.class));
    }


    @GetMapping
    private ResponseEntity<TransactionDTO> getTransactions() {
        List<Transaction> transactions = transactionService.getAllTransaction();
        return ResponseEntity.ok(mapper.map(transactions, TransactionDTO.class));
    }

    @GetMapping("/{id}")
    private ResponseEntity<TransactionDTO> getTransactionByID(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.getTransaction(id);
        return ResponseEntity.ok(mapper.map(transaction, TransactionDTO.class));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<TransactionDTO> deleteById(@PathVariable("id") Long id) {
//        transactionService.deleteTransaction(id);
        return ResponseEntity.ok(mapper.map(id, TransactionDTO.class));
    }


}
