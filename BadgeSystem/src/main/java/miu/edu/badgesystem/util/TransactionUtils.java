package miu.edu.badgesystem.util;

import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.Transaction;

import java.time.LocalDate;

public class TransactionUtils {

    public static Transaction mapToTransaction(Location location, Membership membership){
        Transaction transaction= new Transaction();
        transaction.setLocation(location);
        transaction.setMembership(membership);
        transaction.setStatus('Y');
        transaction.setDate(LocalDate.now());

        return transaction;
    }
}
