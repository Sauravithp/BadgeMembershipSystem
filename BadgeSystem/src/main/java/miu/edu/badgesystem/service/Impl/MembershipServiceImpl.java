package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.MembershipRequestDTO;
import miu.edu.badgesystem.dto.response.MembershipResponseDTO;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.repository.MembershipRepository;
import miu.edu.badgesystem.service.MembershipService;
import miu.edu.badgesystem.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;


    @Override
    public MembershipResponseDTO findById(Long membershipId) {
        Membership membership = membershipRepository.findById(membershipId).orElseThrow(() -> new NoSuchElementException("Membership with id " + membershipId + " NOT FOUND"));
        return ModelMapperUtil.map(membership, MembershipResponseDTO.class);
    }

    @Override
    public List<MembershipResponseDTO> findAll() {
        List<Membership> memberships = membershipRepository.findAll();
        return memberships.stream().map(role-> ModelMapperUtil.map(role, MembershipResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MembershipResponseDTO save(MembershipRequestDTO roleDTO) {
        Membership membership = ModelMapperUtil.map(roleDTO, Membership.class);
        return ModelMapperUtil.map(membershipRepository.save(membership), MembershipResponseDTO.class);
    }

    @Override
    public void delete(Long membershipId) {
        //TODO
        Membership foundMembership = membershipRepository.findById(membershipId).orElseThrow(() -> new NoSuchElementException("Membership with id " + membershipId + " NOT FOUND"));
        membershipRepository.delete(foundMembership);
    }

    @Override
    public MembershipResponseDTO update(MembershipRequestDTO membershipDTO, Long id) {
        Membership membership = ModelMapperUtil.map(membershipDTO, Membership.class);
        Membership foundMembership = membershipRepository.findById(id)
                .map(ms -> {ms.setStartDate(membership.getStartDate());
                    ms.setEndDate(membership.getEndDate());
                    ms.setStatus(membership.getStatus());
                    ms.setLocation(membership.getLocation());
                    return membershipRepository.save(ms);
                }).orElseThrow(() -> new NoSuchElementException("Membership with this id " + id + " not found"));

        return ModelMapperUtil.map(foundMembership, MembershipResponseDTO.class);
    }
}

