package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM Plan p WHERE p.id=:id AND p.status='Y'")
    Optional<Plan> getActivePlanById(@Param("id")Long id);
}
