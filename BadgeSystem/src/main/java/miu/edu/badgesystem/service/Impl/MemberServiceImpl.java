package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.MemberRequestDTO;
import miu.edu.badgesystem.dto.request.MemberUpdateRequestDTO;
import miu.edu.badgesystem.dto.request.MembershipRequestDTO;
import miu.edu.badgesystem.dto.response.MemberResponseDTO;
import miu.edu.badgesystem.dto.response.MembershipResponseDTO;
import miu.edu.badgesystem.exception.DataDuplicationException;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.Plan;
import miu.edu.badgesystem.repository.MemberRepository;
import miu.edu.badgesystem.repository.MembershipRepository;
import miu.edu.badgesystem.repository.PlanRepository;
import miu.edu.badgesystem.service.MemberService;
import miu.edu.badgesystem.service.MembershipService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembershipService membershipService;


    @Override
    public MemberResponseDTO findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new NoContentFoundException("No content found");
        });
        return ModelMapperUtils.map(member, MemberResponseDTO.class);
    }

    @Override
    // TODO: 5/16/22
    public List<MemberResponseDTO> findAll() {
        List<Member> members = memberRepository.findAll();
        if (members.isEmpty()) {
            throw new NoContentFoundException("Member(s) is empty, No data found");
        }
        return members.stream().map(role -> ModelMapperUtils.map(role, MemberResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    // TODO: 5/16/22
    public MemberResponseDTO save(MemberRequestDTO memberDTO) {
        Member member = memberRepository.getMemberByName(memberDTO.getEmailAddress());
        if (Objects.nonNull(member)) {
            throw new DataDuplicationException("Member with email address" + memberDTO.getEmailAddress() + "already exists");
        }

        Member memberToSave = ModelMapperUtils.map(memberDTO, Member.class);
        memberToSave.setStatus('Y');
        List<Membership> membershipResponseDTOS = membershipService.save(memberToSave, memberDTO.getMemberships());;
//        memberToSave.setBadges(memberDTO.getBadges());
        memberToSave.setMemberships(membershipResponseDTOS);
        memberRepository.save(memberToSave);
        MemberResponseDTO responseDTO = ModelMapperUtils.map(memberToSave, MemberResponseDTO.class);
        return responseDTO;
    }

    @Override
    public void delete(Long memberId) {
        //TODO
        Member foundMember = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new NoContentFoundException("No Content Found");
        });
        foundMember.setStatus('D');
        memberRepository.save(foundMember);
    }

    @Override
    public MemberResponseDTO update(MemberUpdateRequestDTO memberDTO, Long id) {
        Member member = ModelMapperUtils.map(memberDTO, Member.class);
        Member alreadyMember = memberRepository.getUpdateMemberByName(member.getEmailAddress(),id);

        if (Objects.nonNull(alreadyMember)) {
            throw new DataDuplicationException("Member with email address" + alreadyMember.getEmailAddress() + "already exists");
        }

        Member foundMember = memberRepository.findById(id)
                .map(m -> {
                    m.setFirstName(member.getFirstName());
                    m.setLastName(member.getLastName());
                    m.setStatus(member.getStatus());
                    m.setEmailAddress(member.getEmailAddress());
                    return memberRepository.save(m);
                }).orElseThrow(() -> {
                    throw new NoContentFoundException("No Content found");
                });

        return ModelMapperUtils.map(foundMember, MemberResponseDTO.class);
    }


}
