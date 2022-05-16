package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

@Query(" Select count (t.id) from Transaction where t.membershipId.id=:membershipId AND" +
        "t.locationId.id=:locationId AND t.createdDate BETWEEN :startDate AND :endDate")
Long getTransactionCountByMembershipAndLocationId(@Param("locationId") Long locationId,
                                                  @Param("memberShipId") Long membershipId,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);
}
