package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.MemberRequestDTO;
import miu.edu.badgesystem.dto.request.MemberUpdateRequestDTO;
import miu.edu.badgesystem.dto.response.MemberResponseDTO;

import miu.edu.badgesystem.dto.response.MembershipResponseDTO;
import miu.edu.badgesystem.dto.response.PlanResponseDTO;
import miu.edu.badgesystem.exception.DataDuplicationException;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.Role;
import miu.edu.badgesystem.repository.*;
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
    private MembershipInfoRepository membershipInfoRepository;

    @Autowired
    private PlanRoleInfoRepository planRoleInfoRepository;

    @Autowired
    private MemberRolesService memberRolesService;

    @Autowired
    private MemberRolesRepository memberRolesRepository;

    @Override
    public MemberResponseDTO findById(Long memberId) {
        Member member = memberRepository.getActiveMemberByID(memberId).orElseThrow(() -> {
            throw new NoContentFoundException("No content found");
        });
        MemberResponseDTO memberResponseDTO = ModelMapperUtils.map(member, MemberResponseDTO.class);
        memberResponseDTO.setRoles(memberRolesRepository.getRolesByMemberId(memberId));
        return memberResponseDTO;
    }

    @Override
    public List<MemberResponseDTO> findAll() {
        List<Member> members = memberRepository.getActiveAllMembers();
        if (members.isEmpty()) {
            throw new NoContentFoundException("Member(s) is empty, No data found");
        }
        List<MemberResponseDTO> memberResponseDTOS = new ArrayList<>();
        members.forEach(m -> {
            MemberResponseDTO memberDTOS = ModelMapperUtils.map(m, MemberResponseDTO.class);
            memberDTOS.setRoles(memberRolesRepository.getRolesByMemberId(m.getId()));
            memberResponseDTOS.add(memberDTOS);
        });
        return memberResponseDTOS;
    }

    @Override
    public MemberResponseDTO save(MemberRequestDTO memberDTO) {
        Member member = memberRepository.getMemberByEmailAddress(memberDTO.getEmailAddress());
        if (Objects.nonNull(member)) {
            throw new DataDuplicationException("Member with email address" + memberDTO.getEmailAddress() + "already exists");
        }
        Member memberToSave = ModelMapperUtils.map(memberDTO, Member.class);
        memberToSave.setStatus('Y');
        Member memberDT = memberRepository.save(memberToSave);
        List<Membership> membershipResponseDTOS = membershipService.save(memberToSave, memberDTO.getMemberships());
        membershipInfoService.save(memberToSave, membershipResponseDTOS);
        memberRolesService.save(memberToSave, mapToRole(membershipResponseDTOS));
        MemberResponseDTO responseDTO = ModelMapperUtils.map(memberToSave, MemberResponseDTO.class);
        responseDTO.setRoles(memberRolesRepository.getRolesByMemberId(memberDT.getId()));
        return responseDTO;
    }

    private List<Role> mapToRole(List<Membership> membership) {
        List<Role> role = new ArrayList<>();
        membership.forEach( r -> {
            role.add(r.getPlanRoleInfo().getRole()); }
        );
        return role;
    }

    @Override
    public void delete(Long memberId) {
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
        List<PlanResponseDTO> plansResponseDTO = new ArrayList<>();
        memberships.forEach(m -> {
            List<Role> roles = planRoleInfoRepository.getActiveRoleInfoByPlanID(m.getPlanRoleInfo().getPlan().getId());
            PlanResponseDTO plansResDTO = ModelMapperUtils.map(m.getPlanRoleInfo().getPlan(), PlanResponseDTO.class);
            plansResDTO.setRoles(roles);
            plansResponseDTO.add(plansResDTO);
        });
        return plansResponseDTO;
    }

    @Override
    public List<MembershipResponseDTO> findMemberMemberships(Long id) {
        List<Membership> memberships = membershipInfoRepository.getMembershipByMemberId(id);
        List<MembershipResponseDTO> memberResponseDTOS = new ArrayList<>();
        memberships.forEach(membership -> memberResponseDTOS.add(ModelMapperUtils.map(membership, MembershipResponseDTO.class)));
        return memberResponseDTOS;
    }

}
