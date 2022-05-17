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

import java.time.LocalDate;
import java.util.List;
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
  private final MembershipInfoService membershipInfoService;

  public TransactionServiceImpl(TransactionRepository transactionRepository,
                                PlanRoleInfoRepository planRoleInfoRepository,
                                LocationRepository locationRepository,
                                MembershipRepository membershipRepository,
                                LocationDateRepository locationDateRepository,
                                MembershipInfoService membershipInfoService) {
    this.transactionRepository = transactionRepository;
    this.planRoleInfoRepository = planRoleInfoRepository;
    this.locationRepository = locationRepository;
    this.membershipRepository = membershipRepository;

    this.locationDateRepository = locationDateRepository;
    this.membershipInfoService = membershipInfoService;
  }

  @Override
  public TransactionResponseDTO saveTransaction(TransactionRequestDTO requestDTO) {
    Membership membership = getMembershipById(requestDTO.getMembershipId());
    Location location = getLocationById(requestDTO.getLocationId());
    checkIfPlanCountExceeds(requestDTO, membership);
    checkIfLocationCapacityIsFull(requestDTO, location);
    checkIfAvailableDateAndTime(location);
    Transaction transaction = TransactionUtils.mapToTransaction(location, membership);
    transactionRepository.save(transaction);
    TransactionResponseDTO transactionResponseDTO = ModelMapperUtils.map(transaction, TransactionResponseDTO.class);
    return transactionResponseDTO;
  }

  private void checkIfAvailableDateAndTime(Location location) {
    LocationDate locationDate = locationDateRepository.getLocationDateByLocationId(location.getId());
    checkClosedDate(locationDate);
    checkIfLocationIsAvailable(locationDate);
  }

  private void checkIfLocationIsAvailable(LocationDate locationDate) {
    Integer count = locationDateRepository.checkIfLocationDateIsAvailable(locationDate.getId());
    if (count == 0) {
      throw new BadRequestException("Sorry,Location is not available");
    }
  }

  public void checkClosedDate(LocationDate locationDate) {
    if (locationDate.getHasLocationClosedDate()) {
      List<LocationClosed> locationClosedDates = locationDate.getLocationClosed();
      locationClosedDates.forEach(date -> {
        boolean isEqual = LocalDate.now().isEqual(date.getDate());
        if (isEqual) {
          throw new BadRequestException("Sorry,Location is closed");
        }
      });
    }
  }


  private void checkIfPlanCountExceeds(TransactionRequestDTO requestDTO, Membership membership) {
    PlanRoleInfo planRoleInfo = planRoleInfoRepository.getActivePlanRoleInfoByPlanID(membership.getPlanRoleInfo().getId()).orElseThrow(() -> {
      throw new NoContentFoundException("Plan not found");
    });
    if (planRoleInfo.getPlan().getIsLimited()) {
      LocalDate startDate = DateUtil.getFirstDayOfMonth();
      LocalDate endDate = DateUtil.getEndDayOfMonth();
      Integer transactionCount = transactionRepository.getTransactionCountByMembershipAndLocationId(requestDTO.getLocationId(), requestDTO.getMembershipId(), startDate, endDate);
      Integer count = planRoleInfo.getPlan().getCount();
      if (transactionCount == count) {
        throw new BadRequestException("sorry transaction for this month has exceeded");
      }
    }
  }

  private void checkIfLocationCapacityIsFull(TransactionRequestDTO requestDTO, Location location) {
    Integer occupiedSeatCount = transactionRepository.getOccupiedSeat(requestDTO.getLocationId());
    if (occupiedSeatCount >= location.getCapacity()) {

      throw new BadRequestException("Sorry its Fully Occupied right now");
    }
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
  List<Membership>membershipList =  membershipInfoService.membershipListBymemberId(id);

    return transactionRepository.findAll().
            stream().filter(s->membershipList.contains(s.getMembership())).
            collect(Collectors.toList());
  }

  //
  //    @Override
  //    public void deleteTransaction(Long id) {
  ////        transactionRipository.deleteTransaction(transactionRipository.findById(id));
  //    }


}
