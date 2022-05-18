package miu.edu.badgesystem.service.Impl;


import miu.edu.badgesystem.dto.request.TransactionRequestDTO;
import miu.edu.badgesystem.dto.response.TransactionResponseDTO;
import miu.edu.badgesystem.exception.BadRequestException;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.*;
import miu.edu.badgesystem.repository.*;
import miu.edu.badgesystem.service.MembershipInfoService;
import miu.edu.badgesystem.service.TransactionService;
import miu.edu.badgesystem.util.DateUtil;
import miu.edu.badgesystem.util.ModelMapperUtils;
import miu.edu.badgesystem.util.TransactionUtils;
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

    private final TransactionRepository transactionRepository;

    private final PlanRoleInfoRepository planRoleInfoRepository;

    private final LocationRepository locationRepository;

    private final MembershipRepository membershipRepository;

    private final LocationDateRepository locationDateRepository;

    @Autowired
    private MembershipInfoService membershipInfoService;

    private final MemberRepository memberRepository;

    private final BadgeRepository badgeRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  PlanRoleInfoRepository planRoleInfoRepository,
                                  LocationRepository locationRepository,
                                  MembershipRepository membershipRepository,
                                  LocationDateRepository locationDateRepository,
                                  MemberRepository memberRepository,
                                  BadgeRepository badgeRepository,
                                  MembershipInfoService membershipInfoService) {
        this.transactionRepository = transactionRepository;
        this.planRoleInfoRepository = planRoleInfoRepository;
        this.locationRepository = locationRepository;
        this.membershipRepository = membershipRepository;

        this.locationDateRepository = locationDateRepository;
        this.membershipInfoService = membershipInfoService;
        this.memberRepository = memberRepository;
        this.badgeRepository = badgeRepository;
    }

    @Override
    public TransactionResponseDTO saveTransaction(TransactionRequestDTO requestDTO) {
        BigInteger membershipId = badgeRepository.getMemberShip(requestDTO.getLocationId(), requestDTO.getBadgeNumber());
        if (membershipId != BigInteger.ZERO) {
            Membership membership = membershipRepository.getActiveMembershipByID(Long.parseLong(membershipId.toString()))
                    .orElseThrow(() -> {
                        throw new NoContentFoundException("Membership NOT Active");
                    });
            Location location = getLocationById(requestDTO.getLocationId());
            Character status = 'Y';
            status = checkIfPlanCountExceeds(requestDTO, membership);
            status = checkIfLocationCapacityIsFull(requestDTO, location);
            status = checkIfAvailableDateAndTime(location);
            Transaction transaction = TransactionUtils.mapToTransaction(location, membership, status);
            transactionRepository.save(transaction);
            TransactionResponseDTO transactionResponseDTO = ModelMapperUtils.map(transaction, TransactionResponseDTO.class);
            return transactionResponseDTO;
        }
        throw new NoContentFoundException("Membership Not Found");
    }

    private Character checkIfAvailableDateAndTime(Location location) {
        LocationDate locationDate = locationDateRepository.getLocationDateByLocationId(location.getId());
        Character status = 'Y';
        status = checkClosedDate(locationDate);
        status = checkIfLocationIsAvailable(locationDate);
        return status;
    }

    private Character checkIfLocationIsAvailable(LocationDate locationDate) {
        Integer count = locationDateRepository.checkIfLocationDateIsAvailable(locationDate.getId());
        if (count == 0) {
            return 'N';
        }
        return 'Y';
    }

    public Character checkClosedDate(LocationDate locationDate) {
        AtomicReference<Character> status = new AtomicReference<>('Y');
        if (locationDate.getHasLocationClosedDate()) {
            List<LocationClosed> locationClosedDates = locationDate.getLocationClosed();
            locationClosedDates.forEach(date -> {
                boolean isEqual = LocalDate.now().
                        isEqual(date.getDate());
                if (isEqual) {
                    status.set('N');
                }
            });
        }
        return status.get();
    }


    private Character checkIfPlanCountExceeds(TransactionRequestDTO requestDTO, Membership membership) {
        Character status = 'Y';
        PlanRoleInfo planRoleInfo = planRoleInfoRepository.getActivePlanRoleInfoByPlanAndRoleID(membership.getPlanRoleInfo().
                getPlan().getId(), membership.getPlanRoleInfo().getRole().getId()).orElseThrow(() -> {
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
                status = 'N';
            }
        }
        return status;
    }

    private Character checkIfLocationCapacityIsFull(TransactionRequestDTO requestDTO, Location location) {
        Integer occupiedSeatCount = transactionRepository.getOccupiedSeat(requestDTO.getLocationId());
        if (occupiedSeatCount >= location.getCapacity()) {

            return 'N';
        }

        return 'Y';
    }

    private Location getLocationById(Long id) {
        Location location = locationRepository.getLocationByID(id);


        if (ObjectUtils.isEmpty(location)) {
            throw new NoContentFoundException("location Not found");
        }

        return location;
    }

    private Membership getMembershipById(Long id) {
        Membership membership = membershipRepository.getById(id);
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

    public List<Transaction> getTransactionByMembershipId(Long id) {
        Membership membership = membershipRepository.getById(id);
        List<Transaction> transactionList = transactionRepository.findAll()
                .stream().filter(s -> s.getMembership().equals(membership))
                .collect(Collectors.toList());
        return transactionList;
    }

    public List<Transaction> getTransactionByMemberId(Long id) {
        List<Membership> membershipList = membershipInfoService.membershipListBymemberId(id);

        return transactionRepository.findAll().
                stream().filter(s -> membershipList.contains(s.getMembership())).
                collect(Collectors.toList());
    }

    //
    //    @Override
    //    public void deleteTransaction(Long id) {
    ////        transactionRipository.deleteTransaction(transactionRipository.findById(id));
    //    }


}
