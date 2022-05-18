package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Badge;
import miu.edu.badgesystem.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge,Long> {

    @Query(value = "SELECT   \n" +
            "DISTINCT ms.id \n" +
            "from Badge b \n" +
            "JOIN `Member` m ON m.id=b.member_id \n" +
            "JOIN MembershipInfo mi ON mi.member_id=m.id \n" +
            "JOIN Membership ms ON ms.id=mi.membership_id \n" +
            "JOIN Location l ON l.id=ms.location_id \n" +
            "WHERE l.id=:locationId\n" +
            "AND b.badge_number=:badgeNumber",nativeQuery = true)
    BigInteger getMemberShip(@Param("locationId") Long locationId, @Param("badgeNumber") String badgeNumber);

    @Query(value = "SELECT b FROM Badge b WHERE b.id=:id AND b.status='Y'")
    Optional<Badge> getActiveBadgeById(@Param("id") Long id);

    @Query(value = "SELECT b FROM Badge b WHERE  b.status='Y'")
    Optional<List<Badge>> getAllActiveBadge();
}
