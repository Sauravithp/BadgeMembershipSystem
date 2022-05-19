package miu.edu.transactionmanagementsystem.controller;


import miu.edu.transactionmanagementsystem.dto.request.TransactionRequestDTO;
import miu.edu.transactionmanagementsystem.dto.response.TransactionResponseDTO;
import miu.edu.transactionmanagementsystem.model.Transaction;
import miu.edu.transactionmanagementsystem.service.TransactionService;
import miu.edu.transactionmanagementsystem.util.ListMapper;
import miu.edu.transactionmanagementsystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    private ListMapper<Transaction, TransactionResponseDTO> listMapper;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    private ResponseEntity<TransactionResponseDTO> save(
            @RequestBody TransactionRequestDTO transactionDto,
            @RequestHeader("Authorization") String token) {

        TransactionResponseDTO savedTransaction = transactionService.saveTransaction(transactionDto, token);
        System.out.println(savedTransaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping
    private ResponseEntity<?> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransaction();
        return ResponseEntity.ok(listMapper.mapList(transactions, new TransactionResponseDTO()));
    }

    @GetMapping("/{id}")
    private ResponseEntity<TransactionResponseDTO> getTransactionByID(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.getTransaction(id);
        return ResponseEntity.ok(ModelMapperUtils.map(transaction, TransactionResponseDTO.class));
    }

    @GetMapping("/badge/{id}")
    private ResponseEntity<?> getAllBadgeTransactions(@PathVariable("id") Long id) {
        List<Transaction> transactionList = transactionService.getAllBadgeTransaction(id);
        return ResponseEntity.ok(listMapper.mapList(transactionList, new TransactionResponseDTO()));
    }

    @GetMapping("/members/{id}")
    private ResponseEntity<?> getTransactionByMembershipId(@PathVariable("id") Long id,
                                                           @RequestHeader("Authorization") String token) {
        List<Transaction> transactionList = transactionService.getTransactionByMembershipId(id, token);
        return ResponseEntity.ok(listMapper.mapList(transactionList, new TransactionResponseDTO()));
    }

    @GetMapping("/member/{id}")
    private ResponseEntity<?> getTransactionByMemberId(@PathVariable("id")Long id,
                                                       @RequestHeader("Authorization") String token){
        System.out.println("BOOM transaction controller");
        List<Transaction> transactionList = transactionService.getTransactionByMemberId(id,token);
        return ResponseEntity.ok(listMapper.mapList(transactionList, new TransactionResponseDTO()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
