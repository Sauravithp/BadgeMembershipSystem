package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.MembershipRequestDTO;
import miu.edu.badgesystem.dto.response.MembershipResponseDTO;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.*;
import miu.edu.badgesystem.repository.LocationRepository;
import miu.edu.badgesystem.repository.MemberRolesRepository;
import miu.edu.badgesystem.repository.MembershipRepository;
import miu.edu.badgesystem.repository.PlanRepository;
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
    private LocationRepository locationRepository;

    @Autowired
    private MemberRolesService memberRolesService;

    @Override
    public MembershipResponseDTO findById(Long membershipId) {
        Membership membership = membershipRepository.findById(membershipId).orElseThrow(() -> {throw new NoContentFoundException("Membership with id " + membershipId + " NOT FOUND");});
        return ModelMapperUtils.map(membership, MembershipResponseDTO.class);
    }

    @Override
    public List<MembershipResponseDTO> findAll() {
        List<Membership> memberships = membershipRepository.findAll();
        if(memberships.isEmpty()) {
            throw new NoContentFoundException("Member(s) is empty, No data found");
        }
        return memberships.stream().map(role -> ModelMapperUtils.map(role, MembershipResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<MembershipResponseDTO> save(Member member, List<MembershipRequestDTO> memberShips) {
        List<Membership> toBeSaved = new ArrayList<>();
        memberShips.forEach(memberShip -> {
            Plan plan = getPlanById(memberShip.getPlan());
            //TODO TO BE TESTED!!
            List<MemberRoles> m = memberRolesService.save(member, plan.getRoles()) ;

            Location location = getLocationById(memberShip.getLocation());
            toBeSaved.add(mapToMemberShip(memberShip, location, plan));

        });
        membershipRepository.saveAll(toBeSaved);

        return mapMemberShipResponseList(toBeSaved);
    }

    @Override
    public void delete(Long membershipId) {
        //TODO
        Membership foundMembership = membershipRepository.findById(membershipId).orElseThrow(() ->
        {throw new NoContentFoundException("Membership with id " + membershipId + " NOT FOUND");});
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
                    throw new NoContentFoundException("No Content with the" + id + "found");
                });

        return ModelMapperUtils.map(foundMembership, MembershipResponseDTO.class);
    }

    private Plan getPlanById(Long id) {
        return planRepository.getActivePlanById(id).orElseThrow(() -> {
            throw new NoContentFoundException("Sorry, Plan is not active");
        });
    }

    private Location getLocationById(Long id) {
        return locationRepository.getActivePlanById(id).orElseThrow(() -> {
            throw new NoContentFoundException("Sorry, Location is not active");
        });
    }

    public Membership mapToMemberShip(MembershipRequestDTO requestDTO, Location location, Plan plan) {
        Membership membership = new Membership();
        membership.setEndDate(requestDTO.getEndDate());
        membership.setStartDate(requestDTO.getStartDate());
        membership.setStatus('Y');
        membership.setLocation(location);
        membership.setPlan(plan);
        return membership;
    }

    public MembershipResponseDTO mapToMemberShip(Membership membership) {
        MembershipResponseDTO responseDTO = new MembershipResponseDTO();
        responseDTO.setEndDate(membership.getEndDate());
        responseDTO.setStartDate(membership.getStartDate());
        responseDTO.setStatus(membership.getStatus());
        responseDTO.setLocation(membership.getLocation());
        responseDTO.setPlan(membership.getPlan());
        return responseDTO;
    }

    public List<MembershipResponseDTO> mapMemberShipResponseList(List<Membership> memberships) {
        List<MembershipResponseDTO> membershipResponseDTOS = new ArrayList<>();

        memberships.forEach(membership -> {
            membershipResponseDTOS.add(mapToMemberShip(membership));
        });

        return membershipResponseDTOS;
    }


}

