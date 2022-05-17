package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.response.MinimumMemberShipResponseDTO;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.Membership;

import java.util.List;

public interface MembershipInfoService{
    void save(Member member, List<Membership> membership);

    List<MinimumMemberShipResponseDTO> getMemberShipByBadgeNumber(String badgeNumber);
}
