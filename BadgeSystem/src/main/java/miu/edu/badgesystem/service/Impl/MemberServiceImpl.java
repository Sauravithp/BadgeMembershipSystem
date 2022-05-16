package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.MemberRequestDTO;
import miu.edu.badgesystem.dto.response.MemberResponseDTO;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.repository.MemberRepository;
import miu.edu.badgesystem.service.MemberService;
import miu.edu.badgesystem.util.ModelMapperUtil;
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


    @Override
    public MemberResponseDTO findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Role with id " + memberId + " NOT FOUND"));
        return ModelMapperUtil.map(member, MemberResponseDTO.class);
    }

    @Override
    public List<MemberResponseDTO> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(role-> ModelMapperUtil.map(role, MemberResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MemberResponseDTO save(MemberRequestDTO memberDTO) {
        Member member = ModelMapperUtil.map(memberDTO, Member.class);
        return ModelMapperUtil.map(memberRepository.save(member), MemberResponseDTO.class);
    }

    @Override
    public void delete(Long memberId) {
        //TODO
        Member foundMember = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Role with id " + memberId + " NOT FOUND"));
        memberRepository.delete(foundMember);
    }

    @Override
    public MemberResponseDTO update(MemberRequestDTO memberDTO, Long id) {
        Member member = ModelMapperUtil.map(memberDTO, Member.class);
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

        return ModelMapperUtil.map(foundMember, MemberResponseDTO.class);
    }
}
