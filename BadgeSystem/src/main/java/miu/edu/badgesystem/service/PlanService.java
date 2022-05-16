package miu.edu.badgesystem.service;


import miu.edu.badgesystem.dto.request.PlanRequestDTO;
import miu.edu.badgesystem.dto.response.PlanResponseDTO;

import java.util.List;

public interface PlanService {

    PlanResponseDTO findById(Long planId);

    List<PlanResponseDTO> findAll();

    PlanResponseDTO save(PlanRequestDTO planDTO);

    void delete(Long planId);

    PlanResponseDTO update(PlanRequestDTO planDTO, Long id);
}
