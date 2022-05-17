package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.MembershipInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipInfoRepository extends JpaRepository<MembershipInfo,Long> {

}
