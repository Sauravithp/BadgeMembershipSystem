package miu.edu.transactionmanagementsystem.service.impl;


import miu.edu.transactionmanagementsystem.dto.request.TransactionRequestDTO;
import miu.edu.transactionmanagementsystem.dto.response.TransactionResponseDTO;
import miu.edu.transactionmanagementsystem.exception.NoContentFoundException;
import miu.edu.transactionmanagementsystem.feign.BadgeSystemFeign;
import miu.edu.transactionmanagementsystem.model.*;
import miu.edu.transactionmanagementsystem.repository.TransactionRepository;
import miu.edu.transactionmanagementsystem.service.TransactionService;
import miu.edu.transactionmanagementsystem.util.DateUtil;
import miu.edu.transactionmanagementsystem.util.ModelMapperUtils;
import miu.edu.transactionmanagementsystem.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private  TransactionRepository transactionRepository;

    @Autowired
    private BadgeSystemFeign badgeSystemFeign;




    @Override
    public TransactionResponseDTO saveTransaction(TransactionRequestDTO requestDTO, String token) {
        BigInteger membershipId=badgeSystemFeign.getMemberShip(requestDTO.getLocationId(),requestDTO.getBadgeNumber(), token);
        if(membershipId!=BigInteger.ZERO){
            Membership membership = badgeSystemFeign.getActiveMembershipByID(Long.parseLong(membershipId.toString()), token)
                    .orElseThrow(() -> {throw new NoContentFoundException("Membership NOT Active");});
            Location location = getLocationById(requestDTO.getLocationId(), token);
            Character status='Y';
            status=checkIfPlanCountExceeds(requestDTO, membership, token);
            status=checkIfLocationCapacityIsFull(requestDTO,location);
            status=checkIfAvailableDateAndTime(location, token);
            Transaction transaction = TransactionUtils.mapToTransaction(location,membership,status);
            transactionRepository.save(transaction);
            TransactionResponseDTO transactionResponseDTO = ModelMapperUtils.map(transaction, TransactionResponseDTO.class);
            return transactionResponseDTO;
        }
        throw new NoContentFoundException("Membership Not Found");
    }

    private Character checkIfAvailableDateAndTime(Location location, String token) {
        LocationDate locationDate=badgeSystemFeign.getLocationDateByLocationId(location.getId(), token);
        Character status='Y';
        status=checkClosedDate(locationDate);
        status=checkIfLocationIsAvailable(locationDate, token);
        return status;
    }

    private Character checkIfLocationIsAvailable(LocationDate locationDate, String token) {
        Integer count=badgeSystemFeign.checkIfLocationDateIsAvailable(locationDate.getId(), token);
        if(count==0){
            return 'N';
        }
        return 'Y';
    }

    public Character checkClosedDate(LocationDate locationDate){
        AtomicReference<Character> status= new AtomicReference<>('Y');
        if(locationDate.getHasLocationClosedDate()){
            List<LocationClosed> locationClosedDates=locationDate.getLocationClosed();
            locationClosedDates.forEach(date->{
                boolean isEqual = LocalDate.now().
                        isEqual(date.getDate());
                if(isEqual){
                    status.set('N');
                }
            });
        }
        return status.get();
    }


    private Character checkIfPlanCountExceeds(TransactionRequestDTO requestDTO, Membership membership, String token) {
        Character status='Y';
        PlanRoleInfo planRoleInfo=badgeSystemFeign.getActivePlanRoleInfoByPlanID(membership.getPlanRoleInfo().
                getId(), token).orElseThrow(() -> {
            throw new NoContentFoundException("Plan not found");
        });
        if (planRoleInfo.getPlan().getIsLimited()) {
            LocalDate startDate = DateUtil.getFirstDayOfMonth();
            LocalDate endDate = DateUtil.getEndDayOfMonth();
            Integer transactionCount = transactionRepository.getTransactionCountByMembershipAndLocationId(
                    requestDTO.getLocationId(),
                    membership.getId(), startDate, endDate);
            Integer count = planRoleInfo.getPlan().getCount();
            if (transactionCount == count) {
                status='N';
            }
        }
        return status;
    }

    private Character checkIfLocationCapacityIsFull(TransactionRequestDTO requestDTO,Location location) {
        Integer occupiedSeatCount = transactionRepository.getOccupiedSeat(requestDTO.getLocationId());
        if (occupiedSeatCount >= location.getCapacity()) {

          return 'N';
        }

        return 'Y';
    }

    private Location getLocationById(Long id, String token){
        Location location = badgeSystemFeign.getActiveLocationByID(id, token);


        if (ObjectUtils.isEmpty(location)) {
            throw new NoContentFoundException("location Not found");
        }

        return location;
    }

    private Membership getMembershipById(Long id, String token){
        Membership membership = badgeSystemFeign.getActiveMembershipByID(id, token)
                .orElseThrow(() -> {throw new NoContentFoundException("Membership NOT Active");});
        if (ObjectUtils.isEmpty(membership)) {
            throw new NoContentFoundException("membership Not found");
        }
return membership;
    }
//
//    @Override
//    public void deleteTransaction(Long id) {
////        transactionRipository.deleteTransaction(transactionRipository.findById(id));
//    }

    @Override
    public Transaction getTransaction(Long id) {
        return transactionRepository.getById(id);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getAllBadgeTransaction(Long id) {
//  getTransaction().
        return null;
    }

    @Override
    public List<Transaction> getTransactionByMembershipId(Long id, String token) {
        Membership membership = badgeSystemFeign.getActiveMembershipByID(id, token)
                .orElseThrow(() -> {throw new NoContentFoundException("Membership NOT Active");});
        List<Transaction> transactionList =
                transactionRepository.findAll().stream()
                        .filter(s -> s.getMembership().equals(membership))
                        .collect(Collectors.toList());
        return transactionList;
    }



}
