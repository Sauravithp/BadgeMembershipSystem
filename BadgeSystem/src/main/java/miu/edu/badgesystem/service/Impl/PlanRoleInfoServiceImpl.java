package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.model.Plan;
import miu.edu.badgesystem.model.PlanRoleInfo;
import miu.edu.badgesystem.model.Role;
import miu.edu.badgesystem.repository.PlanRoleInfoRepository;
import miu.edu.badgesystem.service.PlanRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlanRoleInfoServiceImpl implements PlanRoleInfoService {

    @Autowired
    private PlanRoleInfoRepository planRoleInfoRepository;

    @Override
    public void save(Plan plan, List<Role> roles) {
        List<PlanRoleInfo> planRoleInformation = new ArrayList<>();

        roles.forEach(role -> {
            PlanRoleInfo planRoleInfo = new PlanRoleInfo();
            planRoleInfo.setStatus('Y');
            planRoleInfo.setRole(role);
            planRoleInfo.setPlan(plan);
            planRoleInformation.add(planRoleInfo);
        });

        planRoleInfoRepository.saveAll(planRoleInformation);
    }
}
