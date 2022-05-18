package miu.edu.transactionmanagementsystem.feign;

import miu.edu.transactionmanagementsystem.config.FeignClientConfiguration;
import miu.edu.transactionmanagementsystem.model.Location;
import miu.edu.transactionmanagementsystem.model.LocationDate;
import miu.edu.transactionmanagementsystem.model.Membership;
import miu.edu.transactionmanagementsystem.model.PlanRoleInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@FeignClient(name = "badge-system", configuration = FeignClientConfiguration.class)
public interface BadgeSystemFeign {


    @GetMapping("/feign/memberships/{locationId}/{badgeSystem}")
    BigInteger getMemberShip(@PathVariable("locationId") Long locationId,
                             @PathVariable("badgeSystem") String badgeSystem,
                             @RequestHeader("Authorization") String token);

    @GetMapping("/feign/active/membership/{id}")
    Optional<Membership> getActiveMembershipByID(@PathVariable("id") Long id,
                                                 @RequestHeader("Authorization") String token);

    @GetMapping("/feign/location/{id}")
    Location getActiveLocationByID(@PathVariable("id") Long id,
                                   @RequestHeader("Authorization") String token);

    @GetMapping("/feign/locationDates/{id}")
    LocationDate getLocationDateByLocationId(@PathVariable("id") Long id,
                                             @RequestHeader("Authorization") String token);

    @GetMapping("/feign/locationDates/available/{id}")
    Integer checkIfLocationDateIsAvailable(@PathVariable("id") Long id,
                                           @RequestHeader("Authorization") String token);

    @GetMapping("/feign/planRoleIfo/{planId}/{roleId}")
    Optional<PlanRoleInfo> getActivePlanRoleInfoByPlanID(@PathVariable("planId") Long planId,
                                                         @PathVariable("roleId") Long roleId,
                                                         @RequestHeader("Authorization") String token);

    @GetMapping("/memberShips/{id}")
    List<Membership> membershipListByMemberId(@PathVariable("id") Long id,
                                              @RequestHeader("Authorization") String token);

//    @GetMapping("/api/users/find")
//    Optional<UserDTO> findUserByEmail(
//            @RequestHeaders("Authorization") String token, @RequestParam("email") String email);
}
