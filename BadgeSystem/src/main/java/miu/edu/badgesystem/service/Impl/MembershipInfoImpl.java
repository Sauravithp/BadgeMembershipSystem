package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.response.MinimumMemberShipResponseDTO;
import miu.edu.badgesystem.exception.BadRequestException;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.MembershipInfo;
import miu.edu.badgesystem.repository.MembershipInfoRepository;
import miu.edu.badgesystem.service.MembershipInfoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MembershipInfoImpl implements MembershipInfoService {

    private final MembershipInfoRepository membershipInfoRepository;

    public MembershipInfoImpl(MembershipInfoRepository membershipInfoRepository) {
        this.membershipInfoRepository = membershipInfoRepository;
    }

    @Override
    public void save(Member member, List<Membership> memberships) {
        List<MembershipInfo> infos = new ArrayList<>();
        memberships.forEach(membership -> {
            MembershipInfo membershipInfo = new MembershipInfo();
            membershipInfo.setMember(member);
            membershipInfo.setMembership(membership);
            membershipInfo.setStatus('Y');
            infos.add(membershipInfo);
        });

        membershipInfoRepository.saveAll(infos);
    }

    @Override
    public List<MinimumMemberShipResponseDTO> getMemberShipByBadgeNumber(String badgeNumber) {
        return null;
    }

    public List<Membership> membershipListBymemberId(Long id) {

        List<Membership> membershipList = membershipInfoRepository.findAll().stream()
                .filter(s -> s.getMember().getId().equals(id)).map(s -> s.getMembership())
                .collect(Collectors.toList());
        if (membershipList.isEmpty()) {
            throw new BadRequestException("there is no membership available for this member");
        }
        return membershipList;
    }

    @Override
    public List<Membership> deleteByMemberId(Long memberId) {
        List<MembershipInfo> infos = membershipInfoRepository.getMembershipInfoByMemberId(memberId);
        infos.forEach(info -> {
            info.setStatus('D');
        });
        membershipInfoRepository.saveAll(infos);

        return membershipInfoRepository.getMembershipByMemberId(memberId);
    }

    @Override
    public void saveMembership(Member member, Membership membership) {
        MembershipInfo membershipInfo = new MembershipInfo();
        membershipInfo.setMember(member);
        membershipInfo.setMembership(membership);
        membershipInfo.setStatus('Y');
        membershipInfoRepository.save(membershipInfo);
    }

}
