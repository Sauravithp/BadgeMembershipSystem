package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.dto.response.PlanLocationResponseDTO;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.model.Plan;
import miu.edu.badgesystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM Plan p WHERE p.id=:id AND p.status='Y'")
    Optional<Plan> getActivePlanById(@Param("id") Long id);

    @Query("SELECT p FROM Plan p WHERE  p.status='Y'")
    List<Plan> getActiveAllPlans();

    @Query("SELECT p FROM Plan p WHERE p.name=:name")
    Plan getPlanByName(@Param("name") String name);

    @Query("SELECT p FROM Plan p WHERE p.id<>:id AND p.name=:name AND p.status='Y'")
    Plan getUpdatePlanByName(@Param("name") String name, @Param("id") Long id);

    @Query("Select" +
            " DISTINCT(l) as name," +
            " l.status as status," +
            " l.capacity as capacity," +
            " l.description as description" +
            " from Plan p" +
            " LEFT JOIN PlanRoleInfo pri ON p.id=pri.plan.id" +
            " LEFT JOIN Membership m  On pri.id=m.planRoleInfo.id" +
            " LEFT JOIN Location l ON m.location.id=l.id" +
            " WHERE p.id=:planId AND l.status='Y'")
    List<Location> getLocationByPlanId(@Param("planId") Long planId);
}
