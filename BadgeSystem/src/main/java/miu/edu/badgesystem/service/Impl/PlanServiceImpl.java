package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.AddRoleRequestDTO;
import miu.edu.badgesystem.dto.request.PlanRequestDTO;
import miu.edu.badgesystem.dto.request.PlanUpdateRequestDTO;
import miu.edu.badgesystem.dto.response.PlanResponseDTO;
import miu.edu.badgesystem.exception.DataDuplicationException;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Plan;
import miu.edu.badgesystem.model.PlanRoleInfo;
import miu.edu.badgesystem.model.Role;
import miu.edu.badgesystem.repository.PlanRepository;
import miu.edu.badgesystem.repository.PlanRoleInfoRepository;
import miu.edu.badgesystem.repository.RoleRepository;
import miu.edu.badgesystem.service.PlanRoleInfoService;
import miu.edu.badgesystem.service.PlanService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRoleInfoService planRoleInfoService;

    @Autowired
    private PlanRoleInfoRepository planRoleInfoRepository;


    @Override
    public PlanResponseDTO findById(Long planId) {

        List<Role> roles = planRoleInfoRepository.getActiveRoleInfoByPlanID(planId);

        Plan plan = planRepository.getActivePlanById(planId).orElseThrow(() -> {
            throw new NoContentFoundException("Plan not found");
        });

        PlanResponseDTO pDTO = ModelMapperUtils.map(plan, PlanResponseDTO.class);
        pDTO.setRoles(roles);
        return pDTO;
    }

    @Override
    public List<PlanResponseDTO> findAll() {
        List<Plan> plans = planRepository.getActiveAllPlans();
        if (plans.isEmpty()) {
            throw new NoContentFoundException("Plan(s) is empty, No data found");
        }

        List<PlanResponseDTO> planResponseDTOS = plans.stream().map(plan -> {
            List<Role> r = planRoleInfoRepository.getActiveRoleInfoByPlanID(plan.getId());
            PlanResponseDTO p = ModelMapperUtils.map(plan, PlanResponseDTO.class);
            p.setRoles(r);
            return p;
        }).collect(Collectors.toList());

        return planResponseDTOS;
    }

    @Override
    public PlanResponseDTO save(PlanRequestDTO planDTO) {
        Plan checkIfPlanExists = planRepository.getPlanByName(planDTO.getName());
        if (Objects.nonNull(checkIfPlanExists)) {
            throw new DataDuplicationException("Plan with name" + planDTO.getName() + "already exists");
        }
        List<Role> roles = getRolesByID(planDTO.getRolesId());
        Plan planToSave = new Plan();
        planToSave.setName(planDTO.getName());
        planToSave.setDescription(planDTO.getDescription());
        planToSave.setCount(planDTO.getCount());
        planToSave.setIsLimited(planDTO.getIsLimited());
        planToSave.setStatus('Y');
        Plan plan = planRepository.save(planToSave);
        planRoleInfoService.save(plan, roles);
        PlanResponseDTO responseDTO = ModelMapperUtils.map(plan, PlanResponseDTO.class);
        responseDTO.setRoles(roles);
        return responseDTO;
    }

    private List<Role> getRolesByID(List<Long> rolesId) {
        List<Role> roles = new ArrayList<>();
        rolesId.forEach(role -> {
            Role toBeSaved = roleRepository.getActiveRoleByID(role).orElseThrow(() -> {
                throw new NoContentFoundException("No Content found");
            });

            roles.add(toBeSaved);
        });
        return roles;
    }

    @Override
    public void delete(Long planId) {
        //TODO
        Plan foundPlan = planRepository.getActivePlanById(planId).orElseThrow(() -> {
            throw new NoContentFoundException("Plan not found");
        });
        foundPlan.setStatus('D');
        List<PlanRoleInfo> planRoleInfos=planRoleInfoRepository.getActiveInfoByPlanID(foundPlan.getId());
        planRoleInfos.forEach(role->{
            role.setStatus('D');
        });
        planRepository.save(foundPlan);
    }

    @Override
    public PlanResponseDTO update(PlanUpdateRequestDTO planDTO, Long id) {
        Plan plan = ModelMapperUtils.map(planDTO, Plan.class);
        Plan alreadyPlan = planRepository.getUpdatePlanByName(plan.getName(), id);

        if (Objects.nonNull(alreadyPlan)) {
            throw new DataDuplicationException("Plan with name" + alreadyPlan.getName() + "already exists");
        }

        Plan foundPlan = planRepository.findById(id)
                .map(p -> {
                    p.setName(plan.getName());
                    p.setDescription(plan.getDescription());
                    p.setStatus(plan.getStatus());
                    p.setCount(plan.getCount());
                    p.setIsLimited(plan.getIsLimited());
                    return planRepository.save(p);
                }).orElseThrow(() -> {
                    throw new NoContentFoundException("No Content found");
                });

        return ModelMapperUtils.map(foundPlan, PlanResponseDTO.class);
    }

    @Override
    public void addRolesToExistingPlan(AddRoleRequestDTO planDTO, Long planId) {
       Plan plan= planRepository.findById(planId).orElseThrow(()->{
           throw new NoContentFoundException("Plan not found");});

        List<PlanResponseDTO> planResponseDTOS = planDTO.getRolesId().stream().map(roleId -> {
            Role role = roleRepository.getActiveRoleByID(roleId).orElseThrow(()->{
                throw new NoContentFoundException("Plan not found");});
            PlanRoleInfo planRoleInfo=new PlanRoleInfo();
            planRoleInfo.setPlan(plan);
            planRoleInfo.setRole(role);
            planRoleInfo.setStatus('Y');
            planRoleInfoRepository.save(planRoleInfo);
            PlanResponseDTO p = ModelMapperUtils.map(plan, PlanResponseDTO.class);
            return p;
        }).collect(Collectors.toList());



    }

    @Override
    public void removeRoleFromPlan(Long planId, Long roleId) {
        List<PlanRoleInfo> planRoleInfos=planRoleInfoRepository.getAllActivePlanRoleInfoByPlanAndRoleID(planId,roleId);

        if(planRoleInfos.isEmpty()){
            throw new NoContentFoundException("Role not found");
        }
        planRoleInfos.forEach(planRoleInfo -> {
            planRoleInfo.setStatus('D');
        });
        planRoleInfoRepository.saveAll(planRoleInfos);
    }
}

