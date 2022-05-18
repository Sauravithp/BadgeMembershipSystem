package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.BadgeRequestDTO;
import miu.edu.badgesystem.dto.request.MemberRequestDTO;
import miu.edu.badgesystem.dto.request.MemberUpdateRequestDTO;
import miu.edu.badgesystem.dto.response.*;
import miu.edu.badgesystem.exception.BadRequestException;
import miu.edu.badgesystem.exception.DataDuplicationException;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Badge;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.MemberRoles;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.Role;
import miu.edu.badgesystem.repository.*;
import miu.edu.badgesystem.service.MemberRolesService;
import miu.edu.badgesystem.service.MemberService;
import miu.edu.badgesystem.service.MembershipInfoService;
import miu.edu.badgesystem.service.MembershipService;
import miu.edu.badgesystem.util.ListMapper;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static miu.edu.badgesystem.util.RandomNumberUtil.createBadgeNumber;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private MembershipInfoService membershipInfoService;

    @Autowired
    ListMapper<Membership, MembershipResponseDTO> membershipListMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MemberRolesService memberRolesService;

    @Autowired
    private MembershipInfoRepository membershipInfoRepository;

    @Autowired
    private PlanRoleInfoRepository planRoleInfoRepository;

    @Override
    public MemberResponseDTO findById(Long memberId) {
        Member member = memberRepository.getActiveMemberByID(memberId).orElseThrow(() -> {
            throw new NoContentFoundException("No content found");
        });
        return ModelMapperUtils.map(member, MemberResponseDTO.class);
    }

    @Override
    public List<MemberResponseDTO> findAll() {
        List<Member> members = memberRepository.getActiveAllMembers();
        if (members.isEmpty()) {
            throw new NoContentFoundException("Member(s) is empty, No data found");
        }
        return members.stream().map(member -> ModelMapperUtils.map(member, MemberResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MemberResponseDTO save(MemberRequestDTO memberDTO) {
        Member member = memberRepository.getMemberByEmailAddress(memberDTO.getEmailAddress());
        if (Objects.nonNull(member)) {
            throw new DataDuplicationException("Member with email address" + memberDTO.getEmailAddress() + "already exists");
        }
        Member memberToSave = ModelMapperUtils.map(memberDTO, Member.class);
        memberToSave.setStatus('Y');
        memberRepository.save(memberToSave);
        List<Membership> membershipResponseDTOS = membershipService.save(memberToSave, memberDTO.getMemberships());
        membershipInfoService.save(memberToSave, membershipResponseDTOS);
        memberToSave.setBadges(Arrays.asList(createBadgeFirstTime()));
        memberRolesService.save(memberToSave, mapToRole(membershipResponseDTOS));
        MemberResponseDTO responseDTO = ModelMapperUtils.map(memberToSave, MemberResponseDTO.class);
        responseDTO.setBadgeNumber(memberToSave.getBadges().get(0).getBadgeNumber());
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
    public BadgeResponseDTO createBadgeForAMember(BadgeRequestDTO dto, Long memberId) {
        Badge badge = ModelMapperUtils.map(dto, Badge.class);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Member with id " + memberId + " NOT FOUND"));
        List<Badge> badges = member.getBadges();
        if(badges.stream().anyMatch(badge1 -> badge1.getBadgeNumber().equals(dto.getBadgeNumber()))){
            throw new BadRequestException("badgeNumber is already exist");
        }
        badges.forEach(oneBadge -> {
            if(oneBadge.getStatus() == 'Y'){
                oneBadge.setStatus('N');
            }
        });
        badges.add(badge);
        member.setBadges(badges);
        member =memberRepository.saveAndFlush(member);
        Badge updatedBadge = member.getBadges().stream().
                filter(b -> b.getStatus() == 'Y')
                .findFirst().orElseThrow(
                        () -> new NoSuchElementException("Badge with status Y NOT FOUND")
                );
        return ModelMapperUtils.map(updatedBadge, BadgeResponseDTO.class);
    }

    @Override
    public List<Membership> getMembershipsByBadgeNumber(String badgeNumber){
        return memberRepository.getMembershipsByBadge(badgeNumber);
    }

    @Override
    public List<Badge> getBadgesByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(()-> new BadRequestException("Member is not found")).getBadges();
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

    @Override
    public List<TransactionResponseDTO> findMemberTransactions(Long id) {
        return null;
    }

    @Override
    public Badge createBadgeFirstTime() {
        Badge badge =new Badge();
        badge.setStatus('Y');
        badge.setBadgeNumber(createBadgeNumber());
        return badge;
    }



}
