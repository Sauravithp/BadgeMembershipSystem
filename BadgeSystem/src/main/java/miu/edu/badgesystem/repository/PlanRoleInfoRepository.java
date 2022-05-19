package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.PlanRoleInfo;
import miu.edu.badgesystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRoleInfoRepository extends JpaRepository<PlanRoleInfo, Long> {

    @Query("SELECT pr FROM PlanRoleInfo pr WHere pr.plan.id=:planId AND pr.role.id=:roleId AND pr.status='Y'")
    Optional<PlanRoleInfo> getActivePlanRoleInfoByPlanAndRoleID(@Param("planId") Long planId,
                                                                @Param("roleId") Long roleId);

    @Query("SELECT pr FROM PlanRoleInfo pr WHere pr.plan.id=:planId AND pr.role.id=:roleId AND pr.status='Y'" )
    List<PlanRoleInfo> getAllActivePlanRoleInfoByPlanAndRoleID(@Param("planId") Long planId,
                                                               @Param("roleId") Long roleId);


    @Query("SELECT pr FROM PlanRoleInfo pr WHere pr.plan.id=:planId AND pr.status='Y'")
    Optional<PlanRoleInfo> getActivePlanRoleInfoByPlanID(@Param("planId") Long planId);

    @Query("SELECT pr.role FROM PlanRoleInfo pr WHere pr.plan.id=:planId AND pr.status='Y'")
    List<Role> getActiveRoleInfoByPlanID(@Param("planId") Long planId);

    @Query("SELECT pr FROM PlanRoleInfo pr WHere pr.plan.id=:planId AND pr.status='Y'" )
    List<PlanRoleInfo> getActiveInfoByPlanID(@Param("planId") Long planId);


}
