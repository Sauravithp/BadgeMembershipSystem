package miu.edu.badgesystem.controller.feign;

import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.PlanRoleInfo;
import miu.edu.badgesystem.service.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private FeignService feignService;


    @GetMapping("/memberships/{locationId}/{badgeSystem}")
    public BigInteger getMemberShip(@PathVariable("locationId") Long locationId,
                             @PathVariable("badgeSystem") String badgeSystem){
        return feignService.getMemberShip(locationId,badgeSystem);

    }

    @GetMapping("/active/membership/{id}")
    public Optional<Membership> getActiveMembershipByID(@PathVariable("id") Long id){
        return feignService.getActiveMembershipByID(id);
    }

    @GetMapping("/location/{id}")
    public Location getActiveLocationByID(@PathVariable("id") Long id){
        return feignService.getActiveLocationByID(id);
    }

    @GetMapping("/locationDates/{id}")
    public LocationDate getLocationDateByLocationId(@PathVariable("id") Long id){
       return feignService.getLocationDateByLocationId(id);
    }

    @GetMapping("/locationDates/available/{id}")
    public Integer checkIfLocationDateIsAvailable(@PathVariable("id") Long id){
        return feignService.checkIfLocationDateIsAvailable(id);
    }

    @GetMapping("/planRoleIfo/{planId}/{roleId}")
    public Optional<PlanRoleInfo> getActivePlanRoleInfoByPlanID(@PathVariable("planId") Long planId,
                                                         @PathVariable("roleId") Long roleId){
      return   feignService.getActivePlanRoleInfoByPlanID(planId,roleId);
    }

    @GetMapping("/memberShips/{id}")
    List<Membership> membershipListByMemberId(@PathVariable("id") Long id){
        return   feignService.getMembershipListByMemberId(id);

    }
}