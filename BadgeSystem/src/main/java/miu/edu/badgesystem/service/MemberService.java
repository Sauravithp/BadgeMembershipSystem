package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.request.BadgeRequestDTO;
import miu.edu.badgesystem.dto.request.MemberRequestDTO;
import miu.edu.badgesystem.dto.response.BadgeResponseDTO;
import miu.edu.badgesystem.dto.response.MemberResponseDTO;

import java.util.List;

public interface MemberService {

    MemberResponseDTO findById(Long memberId);

    List<MemberResponseDTO> findAll();

    MemberResponseDTO save(MemberRequestDTO memberDTO);

    void delete(Long memberId);

    MemberResponseDTO update(MemberRequestDTO memberDTO, Long id);

    BadgeResponseDTO createBadgeForAMember(BadgeRequestDTO dto, Long memberId);
}
