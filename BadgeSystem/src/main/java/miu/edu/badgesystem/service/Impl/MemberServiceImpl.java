package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.MemberRequestDTO;
import miu.edu.badgesystem.dto.request.MemberUpdateRequestDTO;
import miu.edu.badgesystem.dto.response.MemberResponseDTO;

import miu.edu.badgesystem.dto.response.PlanResponseDTO;
import miu.edu.badgesystem.dto.response.TransactionResponseDTO;
import miu.edu.badgesystem.exception.DataDuplicationException;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.repository.MemberRepository;
import miu.edu.badgesystem.repository.MembershipInfoRepository;
import miu.edu.badgesystem.repository.RoleRepository;
import miu.edu.badgesystem.service.MemberRolesService;
import miu.edu.badgesystem.service.MemberService;
import miu.edu.badgesystem.service.MembershipInfoService;
import miu.edu.badgesystem.service.MembershipService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private MembershipInfoService membershipInfoService;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MemberRolesService memberRolesService;

    @Autowired
    private MembershipInfoRepository membershipInfoRepository;

    @Override
    public MemberResponseDTO findById(Long memberId) {
        Member member = memberRepository.getActiveMemberByID(memberId).orElseThrow(() -> {
            throw new NoContentFoundException("No content found");
        });
        return ModelMapperUtils.map(member, MemberResponseDTO.class);
    }

    @Override
    // TODO: 5/16/22
    public List<MemberResponseDTO> findAll() {
        List<Member> members = memberRepository.getActiveAllMembers();
        if (members.isEmpty()) {
            throw new NoContentFoundException("Member(s) is empty, No data found");
        }
        return members.stream().map(member -> ModelMapperUtils.map(member, MemberResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    // TODO: 5/16/22
    public MemberResponseDTO save(MemberRequestDTO memberDTO) {
        Member member = memberRepository.getMemberByEmailAddress(memberDTO.getEmailAddress());
        if (Objects.nonNull(member)) {
            throw new DataDuplicationException("Member with email address" + memberDTO.getEmailAddress() + "already exists");
        }

        Member memberToSave = ModelMapperUtils.map(memberDTO, Member.class);
        memberToSave.setStatus('Y');
        memberRepository.save(memberToSave);
        List<Membership> membershipResponseDTOS = membershipService.save(memberToSave, memberDTO.getMemberships());
        ;
        membershipInfoService.save(memberToSave, membershipResponseDTOS);
//        memberToSave.setBadges(memberDTO.getBadges());
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
        Member alreadyMember = memberRepository.getUpdateMemberByName(member.getEmailAddress(), id);

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


    @Override
    public List<PlanResponseDTO> findMemberPlans(Long id) {
        List<Membership> memberships = membershipInfoRepository.getMembershipByMemberId(id);
        List<PlanResponseDTO> plans = new ArrayList<>();
        memberships.forEach(m -> plans.add(ModelMapperUtils.map(m.getPlanRoleInfo().getPlan(), PlanResponseDTO.class)));
        return plans;
    }

    @Override
    public List<MemberResponseDTO> findMemberMemberships(Long id) {
        List<Membership> memberships = membershipInfoRepository.getMembershipByMemberId(id);
        List<MemberResponseDTO> memberResponseDTOS = new ArrayList<>();
        memberships.forEach(membership -> memberResponseDTOS.add(ModelMapperUtils.map(membership, MemberResponseDTO.class)));
        return memberResponseDTOS;
    }

    @Override
    public List<TransactionResponseDTO> findMemberTransactions(Long id) {
        return null;
    }


}
