package miu.edu.badgesystem.service.feign.Impl;

import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.PlanRoleInfo;
import miu.edu.badgesystem.repository.*;
import miu.edu.badgesystem.service.MembershipInfoService;
import miu.edu.badgesystem.service.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FeignServiceImpl implements FeignService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationDateRepository locationDateRepository;

    @Autowired
    private PlanRoleInfoRepository planRoleInfoRepository;

    @Autowired
    private MembershipInfoService membershipInfoService;

    @Override
    public BigInteger getMemberShip(Long locationId, String badgeSystem) {
        return badgeRepository.getMemberShip(locationId,badgeSystem);
    }

    @Override
    public Membership findMemberShipById(Long membershipId) {
        Membership membership = membershipRepository.getActiveMembershipByID(Long.parseLong(membershipId.toString()))
                .orElseThrow(() -> {throw new NoContentFoundException("Membership NOT Active");});

        return membership;
    }

    @Override
    public Optional<Membership> getActiveMembershipByID(Long id) {
        return membershipRepository.getActiveMembershipByID(id);
    }

    @Override
    public Location getActiveLocationByID(Long id) {
        return locationRepository.getLocationByID(id);
    }

    @Override
    public LocationDate getLocationDateByLocationId(Long id) {
        return locationDateRepository.getLocationDateByLocationId(id);
    }

    @Override
    public Integer checkIfLocationDateIsAvailable(Long id) {
        return locationDateRepository.checkIfLocationDateIsAvailable(id);
    }

    @Override
    public Optional<PlanRoleInfo> getActivePlanRoleInfoByPlanID(Long planId, Long roleId) {
        return planRoleInfoRepository.getActivePlanRoleInfoByPlanAndRoleID(planId,roleId);
    }

    @Override
    public List<Membership> getMembershipListByMemberId(Long id) {
        return membershipInfoService.membershipListBymemberId(id);
    }
}
