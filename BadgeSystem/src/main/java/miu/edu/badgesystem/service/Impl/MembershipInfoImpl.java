package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.response.MembershipResponseDTO;
import miu.edu.badgesystem.dto.response.MinimumMemberShipResponseDTO;
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
       List<MembershipInfo> infos=new ArrayList<>();
        memberships.forEach(membership -> {
            MembershipInfo membershipInfo=new MembershipInfo();
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
  public List<Membership> membershipListBymemberId(Long id){



    return membershipInfoRepository.findAll().stream()
            .filter(s->s.getMember().getId().equals(id)).map(s->s.getMembership())
            .collect(Collectors.toList());
  }

}
