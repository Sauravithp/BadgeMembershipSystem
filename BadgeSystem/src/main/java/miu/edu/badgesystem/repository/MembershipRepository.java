package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    @Query("SELECT ms FROM Membership ms WHERE ms.id=:id AND ms.status='Y'")
    Optional<Membership> getActiveMembershipByID(@Param("id") Long id);

    @Query("SELECT ms FROM Membership ms WHERE  ms.status='Y'")
    List<Membership> getActiveAllMemberships();
}
