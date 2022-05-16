package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.PlanRequestDTO;
import miu.edu.badgesystem.dto.response.PlanResponseDTO;
import miu.edu.badgesystem.model.Plan;
import miu.edu.badgesystem.repository.PlanRepository;
import miu.edu.badgesystem.service.PlanService;
import miu.edu.badgesystem.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;


    @Override
    public PlanResponseDTO findById(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(() -> new NoSuchElementException("Plan with id " + planId + " NOT FOUND"));
        return ModelMapperUtil.map(plan, PlanResponseDTO.class);
    }

    @Override
    public List<PlanResponseDTO> findAll() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream().map(role-> ModelMapperUtil.map(role, PlanResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PlanResponseDTO save(PlanRequestDTO planDTO) {
        Plan plan = ModelMapperUtil.map(planDTO, Plan.class);
        return ModelMapperUtil.map(planRepository.save(plan), PlanResponseDTO.class);
    }

    @Override
    public void delete(Long planId) {
        //TODO
        Plan foundPlan = planRepository.findById(planId).orElseThrow(() -> new NoSuchElementException("Plan with id " + planId + " NOT FOUND"));
        planRepository.delete(foundPlan);
    }

    @Override
    public PlanResponseDTO update(PlanRequestDTO planDTO, Long id) {
        Plan plan = ModelMapperUtil.map(planDTO, Plan.class);
        Plan foundPlan = planRepository.findById(id)
                .map(p -> {p.setName(plan.getName());
                    p.setDescription(plan.getDescription());
                    p.setStatus(plan.getStatus());
                    p.setCount(plan.getCount());
                    p.setRoles(plan.getRoles());
                    p.setIsLimited(plan.getIsLimited());
                    return planRepository.save(p);
                }).orElseThrow(() -> new NoSuchElementException("Plan with this id " + id + " not found"));

        return ModelMapperUtil.map(foundPlan, PlanResponseDTO.class);
    }
}

