package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.request.MemberRequestDTO;
import miu.edu.badgesystem.dto.request.MemberUpdateRequestDTO;
import miu.edu.badgesystem.dto.response.MemberResponseDTO;

import miu.edu.badgesystem.dto.response.PlanResponseDTO;
import miu.edu.badgesystem.dto.response.TransactionResponseDTO;


import java.util.List;

public interface MemberService {

    MemberResponseDTO findById(Long memberId);

    List<MemberResponseDTO> findAll();

    MemberResponseDTO save(MemberRequestDTO memberDTO);

    void delete(Long memberId);

    MemberResponseDTO update(MemberUpdateRequestDTO memberDTO, Long id);


    List<PlanResponseDTO> findMemberPlans(Long id);

    List<MemberResponseDTO> findMemberMemberships(Long id);

    List<TransactionResponseDTO> findMemberTransactions(Long id);

}
