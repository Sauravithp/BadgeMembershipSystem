package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.PlanRoleInfo;
import miu.edu.badgesystem.service.PlanRoleInfoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRoleInfoRepository extends JpaRepository<PlanRoleInfo,Long> {
}
