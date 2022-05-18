package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.request.MembershipRequestDTO;
import miu.edu.badgesystem.dto.response.MembershipResponseDTO;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.Membership;

import java.util.List;

public interface MembershipService {

    MembershipResponseDTO findById(Long membershipId);

    List<MembershipResponseDTO> findAll();

    List<Membership> save(Member member, List<MembershipRequestDTO> membershipDTO);

    void delete(Long membershipId);

    void deleteAllByMemberId(Long memberId);

    MembershipResponseDTO update(MembershipRequestDTO membershipDTO, Long id);
}
