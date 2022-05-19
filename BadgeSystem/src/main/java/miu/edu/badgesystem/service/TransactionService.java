package miu.edu.badgesystem.service;


import miu.edu.badgesystem.dto.request.TransactionRequestDTO;
import miu.edu.badgesystem.dto.response.TransactionResponseDTO;
import miu.edu.badgesystem.model.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionResponseDTO saveTransaction(TransactionRequestDTO transaction);

    Transaction getTransaction(Long id);

    List<Transaction> getAllTransaction();

    //    void deleteTransaction(Long id);

    List<Transaction>getAllBadgeTransaction(Long id);

    List<Transaction> getTransactionByMembershipId(Long id);

    List<Transaction> getTransactionByMemberId(Long id);
}
