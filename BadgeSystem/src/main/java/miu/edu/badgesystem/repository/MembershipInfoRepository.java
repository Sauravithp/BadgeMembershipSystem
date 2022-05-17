package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.dto.response.MinimumMemberShipResponseDTO;
import miu.edu.badgesystem.model.MembershipInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipInfoRepository extends JpaRepository<MembershipInfo,Long> {
  List<MinimumMemberShipResponseDTO> getMemberShipByBadgeNumber(String badgeNumber);
}
