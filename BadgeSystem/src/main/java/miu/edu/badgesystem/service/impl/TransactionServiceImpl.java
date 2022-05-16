package miu.edu.badgesystem.service.impl;


import miu.edu.badgesystem.dto.request.TransactionRequestDTO;
import miu.edu.badgesystem.dto.response.TransactionResponseDTO;
import miu.edu.badgesystem.exception.BadRequestException;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.Transaction;
import miu.edu.badgesystem.repository.LocationRepository;
import miu.edu.badgesystem.repository.MembershipRepository;
import miu.edu.badgesystem.repository.PlanRepository;
import miu.edu.badgesystem.repository.TransactionRepository;
import miu.edu.badgesystem.service.TransactionService;
import miu.edu.badgesystem.util.DateUtil;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final LocationRepository locationRepository;

    private final MembershipRepository membershipRepository;


    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  LocationRepository locationRepository,
                                  MembershipRepository membershipRepository) {
        this.transactionRepository = transactionRepository;
        this.locationRepository = locationRepository;
        this.membershipRepository = membershipRepository;

    }

    @Override
    public TransactionResponseDTO saveTransaction(TransactionRequestDTO transaction) {
        Membership membership = membershipRepository.getById(transaction.getMembershipId());
        Location location = locationRepository.getLocationByID(transaction.getLocationId());
        if (ObjectUtils.isEmpty(membership)) {
            throw new NoContentFoundException("membership Not found");
        }

        if (ObjectUtils.isEmpty(location)) {
            throw new NoContentFoundException("location Not found");
        }
//        checking if count is exceeded for the member
        if (membership.getPlan().getIsLimited()) {
            LocalDate startDate = DateUtil.getFirstDayOfMonth();
            LocalDate endDate = DateUtil.getEndDayOfMonth();
           Integer transactionCount = transactionRepository.getTransactionCountByMembershipAndLocationId(
                   transaction.getLocationId(),
                   transaction.getMembershipId(), startDate, endDate);
            Integer count = membership.getPlan().getCount();
            if (transactionCount == count) {
                throw new BadRequestException("sorry transaction for this month has exceeded");
            }
        }
        Integer occupiedSeatCount =  transactionRepository.getOccupiedSeat(transaction.getLocationId());
        if(occupiedSeatCount>=location.getCapacity()){

            throw new BadRequestException("Sorry its Fully Occupied right now");
        }

        //saving the transactions
        Transaction transaction1 = new Transaction();
        transaction1.setLocation(location);
        transaction1.setMembership(membership);
        transaction1.setStatus('Y');
        transaction1.setDate(LocalDate.now());

        transactionRepository.save(transaction1);

        TransactionResponseDTO transactionResponseDTO = ModelMapperUtils.map(transaction1, TransactionResponseDTO.class);


        return transactionResponseDTO;
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
