package miu.edu.badgesystem.service.impl;


import miu.edu.badgesystem.dto.request.TransactionRequestDTO;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.Transaction;
import miu.edu.badgesystem.repository.LocationRepository;
import miu.edu.badgesystem.repository.MembershipRepository;
import miu.edu.badgesystem.repository.TransactionRepository;
import miu.edu.badgesystem.service.TransactionService;
import miu.edu.badgesystem.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional

public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public Transaction saveTransaction(TransactionRequestDTO transaction) {
        Membership membership= membershipRepository.getById(transaction.getMembershipId());
        if(ObjectUtils.isEmpty(membership)){
            throw new NoContentFoundException("membership Not found");
        }
        Location location = locationRepository.getLocationByID(transaction.getLocationId());
        if(ObjectUtils.isEmpty(location)){
            throw new NoContentFoundException("location Not found");
        }
        LocalDate startDate= DateUtil.getFirstDayOfMonth();
        LocalDate endDate = DateUtil.getEndDayOfMonth();

        Long transactionCount= transactionRepository.getTransactionCountByMembershipAndLocationId(transaction.getLocationId(),
                transaction.getMembershipId(),startDate,endDate);


        return null;
    }

    @Override
    public Transaction getTransaction(Long id) {
        return transactionRepository.getById(id);
    }

    @Override
    public List<Transaction> getAllTransaction() {

        return transactionRepository.findAll();

    }
//
//    @Override
//    public void deleteTransaction(Long id) {
////        transactionRipository.deleteTransaction(transactionRipository.findById(id));
//    }


}
