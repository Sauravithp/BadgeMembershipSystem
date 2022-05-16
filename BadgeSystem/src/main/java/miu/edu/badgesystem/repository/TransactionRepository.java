package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(" Select count(t.id) from Transaction t where t.membership.id=:membershipId AND " +
            "t.location.id=:locationId AND t.createdDate BETWEEN :startDate AND :endDate")
    Integer getTransactionCountByMembershipAndLocationId(@Param("locationId") Long locationId,
                                                         @Param("membershipId") Long membershipId,
                                                         @Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate);

    //query for calculating the capacity per day
    @Query("Select count(t.id) from Transaction t where  t.createdDate=CURDATE() AND t.location.id=:locationId")
    Integer getOccupiedSeat(@Param("locationId") Long locationId);


}
