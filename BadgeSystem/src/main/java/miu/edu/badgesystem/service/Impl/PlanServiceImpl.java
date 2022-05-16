package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.PlanRequestDTO;
import miu.edu.badgesystem.dto.response.PlanResponseDTO;
import miu.edu.badgesystem.exception.DataDuplicationException;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Plan;
import miu.edu.badgesystem.model.Role;
import miu.edu.badgesystem.repository.PlanRepository;
import miu.edu.badgesystem.service.PlanService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;


    @Override
    public PlanResponseDTO findById(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(() -> {
            throw new NoContentFoundException("Plan id with" + planId + "not found");
        });
        return ModelMapperUtils.map(plan, PlanResponseDTO.class);
    }

    @Override
    public List<PlanResponseDTO> findAll() {
        List<Plan> plans = planRepository.findAll();
        if (plans.isEmpty()) {
            throw new NoContentFoundException("Plan(s) is empty, No data found");
        }
        return plans.stream().map(role-> ModelMapperUtils.map(role, PlanResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PlanResponseDTO save(PlanRequestDTO planDTO) {
        Plan plan = planRepository.getPlanByName(planDTO.getName());
        if (Objects.nonNull(plan)) {
            throw new DataDuplicationException("Member with name" + planDTO.getName() + "already exists");
        }
        Plan planToSave = ModelMapperUtils.map(planDTO, Plan.class);
        planToSave.setStatus('Y');
        return ModelMapperUtils.map(planRepository.save(planToSave), PlanResponseDTO.class);
    }

    @Override
    public void delete(Long planId) {
        //TODO
        Plan foundPlan = planRepository.findById(planId).orElseThrow(() -> {
            throw new NoContentFoundException("Plan with id" + planId + "not found");
        });
        foundPlan.setStatus('D');
        planRepository.save(foundPlan);
    }

    @Override
    public PlanResponseDTO update(PlanRequestDTO planDTO, Long id) {
        Plan plan = ModelMapperUtils.map(planDTO, Plan.class);
        Plan foundPlan = planRepository.findById(id)
                .map(p -> {p.setName(plan.getName());
                    p.setDescription(plan.getDescription());
                    p.setStatus(plan.getStatus());
                    p.setCount(plan.getCount());
                    p.setRoles(plan.getRoles());
                    p.setIsLimited(plan.getIsLimited());
                    return planRepository.save(p);
                }).orElseThrow(() -> {
                    throw new NoContentFoundException("No Content with the" + id + "found");
                });

        return ModelMapperUtils.map(foundPlan, PlanResponseDTO.class);
    }
}

