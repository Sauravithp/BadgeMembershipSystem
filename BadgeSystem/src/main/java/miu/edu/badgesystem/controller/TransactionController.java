package miu.edu.badgesystem.controller;


import miu.edu.badgesystem.dto.request.TransactionRequestDTO;
import miu.edu.badgesystem.dto.response.TransactionResponseDTO;
import miu.edu.badgesystem.model.Transaction;
import miu.edu.badgesystem.service.TransactionService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    private ResponseEntity<TransactionResponseDTO> save(@RequestBody TransactionRequestDTO transactionDto) {

        TransactionResponseDTO savedTransaction = transactionService.saveTransaction(transactionDto);
        System.out.println(savedTransaction);
        return ResponseEntity.ok(savedTransaction);
    }


    @GetMapping
    private ResponseEntity<TransactionResponseDTO> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransaction();
        return ResponseEntity.ok(ModelMapperUtils.map(transactions, TransactionResponseDTO.class));
    }

    @GetMapping("/{id}")
    private ResponseEntity<TransactionResponseDTO> getTransactionByID(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.getTransaction(id);
        return ResponseEntity.ok(ModelMapperUtils.map(transaction, TransactionResponseDTO.class));
    }

//    @DeleteMapping("/{id}")
//    private ResponseEntity<TransactionDTO> deleteById(@PathVariable("id") Long id) {
////        transactionService.deleteTransaction(id);
//        return ResponseEntity.ok(mapper.map(id, TransactionDTO.class));
//    }


}
