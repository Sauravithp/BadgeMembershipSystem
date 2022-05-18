package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.MemberMembershipRequestDTO;
import miu.edu.badgesystem.dto.request.MembershipRequestDTO;
import miu.edu.badgesystem.dto.response.MemberMembershipResponseDTO;
import miu.edu.badgesystem.dto.response.MembershipResponseDTO;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.*;
import miu.edu.badgesystem.repository.*;
import miu.edu.badgesystem.service.MembershipInfoService;
import miu.edu.badgesystem.service.MembershipService;
import miu.edu.badgesystem.util.MembershipDataUtil;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRoleInfoRepository planRoleInfoRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MembershipInfoService membershipInfoService;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MembershipResponseDTO findById(Long membershipId) {
        Membership membership = membershipRepository.getActiveMembershipByID(membershipId).orElseThrow(() -> {
            throw new NoContentFoundException("Membership NOT FOUND");
        });
        return ModelMapperUtils.map(membership, MembershipResponseDTO.class);
    }

    @Override
    public List<MembershipResponseDTO> findAll() {
        List<Membership> memberships = membershipRepository.getActiveAllMemberships();
        if (memberships.isEmpty()) {
            throw new NoContentFoundException("Membership(s) is empty, No data found");
        }
        return memberships.stream().map(membership -> ModelMapperUtils.map(membership, MembershipResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<Membership> save(Member member, List<MembershipRequestDTO> requestDTOs) {
        List<Membership> toBeSaved = new ArrayList<>();
        requestDTOs.forEach(requestDTO -> {
            Plan plan = getPlanById(requestDTO.getPlan());
            Location location = getLocationById(requestDTO.getLocation());
            Role role = getRole(requestDTO.getRole());
            PlanRoleInfo planRoleInfo = getPlanRoleInfo(plan.getId(), role.getId());
            toBeSaved.add(MembershipDataUtil.mapToMemberShip(requestDTO, location, planRoleInfo));
        });
        return membershipRepository.saveAll(toBeSaved);
    }

    @Override
    public void delete(Long membershipId) {
        Membership foundMembership = membershipRepository.findById(membershipId).orElseThrow(() ->
        {
            throw new NoContentFoundException("Membership NOT FOUND");
        });
        foundMembership.setStatus('D');
        membershipRepository.save(foundMembership);
    }

    @Override
    public void deleteAllByMemberId(Long memberId) {
        List<Membership> memberships = membershipInfoService.deleteByMemberId(memberId);
        memberships.forEach(membership -> {
            membership.setStatus('D');
        });
        membershipRepository.saveAll(memberships);
    }

    @Override
    public MembershipResponseDTO update(MembershipRequestDTO membershipDTO, Long id) {
        Membership membership = ModelMapperUtils.map(membershipDTO, Membership.class);
        Membership foundMembership = membershipRepository.findById(id)
                .map(ms -> {
                    ms.setStartDate(membership.getStartDate());
                    ms.setEndDate(membership.getEndDate());
                    ms.setStatus(membership.getStatus());
                    ms.setLocation(membership.getLocation());
                    return membershipRepository.save(ms);
                }).orElseThrow(() -> {
                    throw new NoContentFoundException("No Content found");
                });

        return ModelMapperUtils.map(foundMembership, MembershipResponseDTO.class);
    }

    @Override
    public MemberMembershipResponseDTO saveMembership(MemberMembershipRequestDTO membershipRequestDTO) {
        Plan plan = getPlanById(membershipRequestDTO.getPlan());
        Role role = getRole(membershipRequestDTO.getRole());
        Location location = getLocationById(membershipRequestDTO.getLocation());
        Member member = getMemberById(membershipRequestDTO.getMemberId());
        PlanRoleInfo planRole = getPlanRoleInfo(plan.getId(), role.getId());
        Membership membership = membershipRepository.save(MembershipDataUtil.saveMembership(location,membershipRequestDTO, planRole));
        membershipInfoService.saveMembership(member, membership);
        return ModelMapperUtils.map(membership, MemberMembershipResponseDTO.class);
    }

    private Plan getPlanById(Long id) {
        return planRepository.getActivePlanById(id).orElseThrow(() -> {
            throw new NoContentFoundException("Sorry, Plan is not active");
        });
    }

    private Role getRole(Long id) {
        return roleRepository.getActiveRoleById(id).orElseThrow(() -> {
            throw new NoContentFoundException("Sorry, Role is not active");
        });
    }

    private PlanRoleInfo getPlanRoleInfo(Long planId, Long roleId) {
        return planRoleInfoRepository.getActivePlanRoleInfoByPlanAndRoleID(planId, roleId).orElseThrow(() -> {
            throw new NoContentFoundException("Sorry, Plan with the given role is not active");
        });
    }

    private Location getLocationById(Long id) {
        return locationRepository.getActiveLocationById(id).orElseThrow(() -> {
            throw new NoContentFoundException("Sorry, Location is not active");
        });
    }

    private Member getMemberById(Long id) {
        return memberRepository.getActiveMemberByID(id).orElseThrow(() -> {
            throw new NoContentFoundException("Sorry, Member is not active");
        });
    }

    public List<MembershipResponseDTO> mapMemberShipResponseList(List<Membership> memberships) {
        List<MembershipResponseDTO> membershipResponseDTOS = new ArrayList<>();
        memberships.forEach(membership -> {
            membershipResponseDTOS.add(MembershipDataUtil.mapToMemberShipDTO(membership));
        });
        return membershipResponseDTOS;
    }


}

