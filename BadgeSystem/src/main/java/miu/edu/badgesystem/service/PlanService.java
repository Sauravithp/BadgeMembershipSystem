package miu.edu.badgesystem.service;


import miu.edu.badgesystem.dto.request.AddRoleRequestDTO;
import miu.edu.badgesystem.dto.request.PlanRequestDTO;
import miu.edu.badgesystem.dto.request.PlanUpdateRequestDTO;
import miu.edu.badgesystem.dto.response.PlanResponseDTO;

import java.util.List;

public interface PlanService {

    PlanResponseDTO findById(Long planId);

    List<PlanResponseDTO> findAll();

    PlanResponseDTO save(PlanRequestDTO planDTO);

    void delete(Long planId);

    PlanResponseDTO update(PlanUpdateRequestDTO planDTO, Long id);

    void addRolesToExistingPlan(AddRoleRequestDTO planDTO, Long planId);
}
