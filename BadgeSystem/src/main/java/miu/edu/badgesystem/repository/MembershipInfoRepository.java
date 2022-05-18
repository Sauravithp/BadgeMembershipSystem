package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.MembershipInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipInfoRepository extends JpaRepository<MembershipInfo,Long> {

    @Query("SELECT i.membership From MembershipInfo i where i.member.id=:id")
    List<Membership> getMembershipByMemberId(@Param("id") Long id);

    @Query("SELECT i From MembershipInfo i where i.member.id=:id")
    List<MembershipInfo> getMembershipInfoByMemberId(@Param("id") Long id);

}
