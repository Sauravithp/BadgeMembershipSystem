package miu.edu.badgesystem.service.feign;

import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.PlanRoleInfo;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface FeignService {

    BigInteger getMemberShip(@PathVariable("locationId")Long locationId,@PathVariable("badgeSystem") String badgeSystem);

    Membership findMemberShipById(@PathVariable("membershipId") Long membershipId);

    Optional<Membership> getActiveMembershipByID(@PathVariable("id") Long id);

    Location getActiveLocationByID(@PathVariable("id") Long id);

    LocationDate getLocationDateByLocationId(@PathVariable("id") Long id);

    Integer checkIfLocationDateIsAvailable(@PathVariable("id") Long id);

    Optional<PlanRoleInfo> getActivePlanRoleInfoByPlanID(@PathVariable("planId") Long planId,
                                                         @PathVariable("roleId") Long roleId);

    List<Membership> getMembershipListByMemberId(@PathVariable("id") Long id);


}
