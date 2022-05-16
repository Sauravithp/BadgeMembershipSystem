package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.BadgeRequestDTO;
import miu.edu.badgesystem.dto.request.MemberRequestDTO;
import miu.edu.badgesystem.dto.response.BadgeResponseDTO;
import miu.edu.badgesystem.dto.response.MemberResponseDTO;
import miu.edu.badgesystem.exception.BadRequestException;
import miu.edu.badgesystem.model.Badge;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.repository.BadgeRepository;
import miu.edu.badgesystem.repository.MemberRepository;
import miu.edu.badgesystem.service.MemberService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BadgeRepository badgeRepository;


    @Override
    public MemberResponseDTO findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Role with id " + memberId + " NOT FOUND"));
        return ModelMapperUtils.map(member, MemberResponseDTO.class);
    }

    @Override
    // TODO: 5/16/22
    public List<MemberResponseDTO> findAll() {
        List<Member> members = memberRepository.findAll();
        //members if its empty you need to send an exception
        return members.stream().map(role-> ModelMapperUtils.map(role, MemberResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    // TODO: 5/16/22
    public MemberResponseDTO save(MemberRequestDTO memberDTO) {
        Member member = ModelMapperUtils.map(memberDTO, Member.class);
        //Check for duplicate data
        return ModelMapperUtils.map(memberRepository.save(member), MemberResponseDTO.class);
    }

    @Override
    public void delete(Long memberId) {
        //TODO
        Member foundMember = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Role with id " + memberId + " NOT FOUND"));
        foundMember.setStatus('D');
        memberRepository.save(foundMember);
    }

    @Override
    public MemberResponseDTO update(MemberRequestDTO memberDTO, Long id) {
        Member member = ModelMapperUtils.map(memberDTO, Member.class);
        Member foundMember = memberRepository.findById(id)
                .map(m -> {m.setFirstName(member.getFirstName());
                    m.setLastName(member.getLastName());
                    m.setStatus(member.getStatus());
                    m.setEmailAddress(member.getEmailAddress());
                    m.setMemberships(member.getMemberships());
                    m.setBadges(member.getBadges());
                    m.setMemberRoles(member.getMemberRoles());
                    return memberRepository.save(m);
                }).orElseThrow(() -> new NoSuchElementException("Role with this id " + id + " not found"));

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

}
