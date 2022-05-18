package miu.edu.transactionmanagementsystem.service;




import miu.edu.transactionmanagementsystem.dto.request.TransactionRequestDTO;
import miu.edu.transactionmanagementsystem.dto.response.TransactionResponseDTO;
import miu.edu.transactionmanagementsystem.model.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionResponseDTO saveTransaction(TransactionRequestDTO transaction, String token);
    Transaction getTransaction(Long id);
    List<Transaction> getAllTransaction();
    //    void deleteTransaction(Long id);
    List<Transaction>getAllBadgeTransaction(Long id);
    List<Transaction> getTransactionByMembershipId(Long id, String token);
    List<Transaction> getTransactionByMemberId(Long id, String token);

}
