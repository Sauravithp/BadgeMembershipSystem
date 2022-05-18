package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.MembershipRequestDTO;
import miu.edu.badgesystem.dto.response.MembershipResponseDTO;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.*;
import miu.edu.badgesystem.repository.*;
import miu.edu.badgesystem.service.MemberRolesService;
import miu.edu.badgesystem.service.MembershipService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
            //TODO TO BE TESTED!!
            Location location = getLocationById(requestDTO.getLocation());
            Role role = getRole(requestDTO.getRole());
            PlanRoleInfo planRoleInfo = getPlanRoleInfo(plan.getId(), role.getId());
            toBeSaved.add(mapToMemberShip(requestDTO, location, planRoleInfo));

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
    public MembershipResponseDTO saveMembership(MembershipRequestDTO membershipRequestDTO) {
        Membership membership = ModelMapperUtils.map(membershipRequestDTO, Membership.class);
        membership.setLocation(locationRepository.getLocationByID(membershipRequestDTO.getLocation()));
        return ModelMapperUtils.map(membershipRepository.save(membership), MembershipResponseDTO.class);
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

    public Membership mapToMemberShip(MembershipRequestDTO requestDTO, Location location, PlanRoleInfo planRoleInfo) {
        Membership membership = new Membership();
        membership.setEndDate(requestDTO.getEndDate());
        membership.setStartDate(requestDTO.getStartDate());
        membership.setStatus('Y');
        membership.setLocation(location);
        membership.setPlanRoleInfo(planRoleInfo);
        return membership;
    }

    public MembershipResponseDTO mapToMemberShipDTO(Membership membership) {
        MembershipResponseDTO responseDTO = new MembershipResponseDTO();
        responseDTO.setEndDate(membership.getEndDate());
        responseDTO.setStartDate(membership.getStartDate());
        responseDTO.setStatus(membership.getStatus());
        responseDTO.setLocation(membership.getLocation());
        responseDTO.setPlan(membership.getPlanRoleInfo().getPlan());
        return responseDTO;
    }

    public List<MembershipResponseDTO> mapMemberShipResponseList(List<Membership> memberships) {
        List<MembershipResponseDTO> membershipResponseDTOS = new ArrayList<>();
        memberships.forEach(membership -> {
            membershipResponseDTOS.add(mapToMemberShipDTO(membership));
        });
        return membershipResponseDTOS;
    }


}

