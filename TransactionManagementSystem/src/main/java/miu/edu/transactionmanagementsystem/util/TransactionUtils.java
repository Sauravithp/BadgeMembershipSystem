package miu.edu.transactionmanagementsystem.util;

import miu.edu.transactionmanagementsystem.model.Location;
import miu.edu.transactionmanagementsystem.model.Membership;
import miu.edu.transactionmanagementsystem.model.Transaction;
import miu.edu.transactionmanagementsystem.model.enums.TransactionStatus;

import java.time.LocalDate;

public class TransactionUtils {

    public static Transaction mapToTransaction(Location location, Membership membership, Character status) {
        Transaction transaction = new Transaction();
        transaction.setLocation(location);
        transaction.setMembership(membership);
        transaction.setStatus(status);
        transaction.setDate(LocalDate.now());
        if (status == 'N') {
            transaction.setTransactionStatus(TransactionStatus.DENIED);
        } else {
            transaction.setTransactionStatus(TransactionStatus.ACCESSED);

        }
        transaction.setTransactionNumber(RandomNumberUtil.createTransactionNumber());
        return transaction;
    }
}
