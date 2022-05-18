package miu.edu.badgesystem.util;

import miu.edu.badgesystem.dto.request.MemberMembershipRequestDTO;
import miu.edu.badgesystem.dto.request.MembershipRequestDTO;
import miu.edu.badgesystem.dto.response.MembershipResponseDTO;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.PlanRoleInfo;
import miu.edu.badgesystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MembershipDataUtil {

    public static Membership saveMembership(Location location, MemberMembershipRequestDTO membershipRequestDTO, PlanRoleInfo planRoleInfo) {
        Membership membership = new Membership();
        membership.setLocation(location);
        membership.setStartDate(membershipRequestDTO.getStartDate());
        membership.setEndDate(membershipRequestDTO.getEndDate());
        membership.setStatus(membershipRequestDTO.getStatus());
        membership.setPlanRoleInfo(planRoleInfo);
        return membership;
    }

    public static Membership mapToMemberShip(MembershipRequestDTO requestDTO, Location location, PlanRoleInfo planRoleInfo) {
        Membership membership = new Membership();
        membership.setEndDate(requestDTO.getEndDate());
        membership.setStartDate(requestDTO.getStartDate());
        membership.setStatus('Y');
        membership.setLocation(location);
        membership.setPlanRoleInfo(planRoleInfo);
        return membership;
    }


    public static MembershipResponseDTO mapToMemberShipDTO(Membership membership) {
        MembershipResponseDTO responseDTO = new MembershipResponseDTO();
        responseDTO.setEndDate(membership.getEndDate());
        responseDTO.setStartDate(membership.getStartDate());
        responseDTO.setStatus(membership.getStatus());
        responseDTO.setLocation(membership.getLocation());
        responseDTO.setPlan(membership.getPlanRoleInfo().getPlan());
        return responseDTO;
    }

}
