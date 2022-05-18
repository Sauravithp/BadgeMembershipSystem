package miu.edu.badgesystem.util;

import miu.edu.badgesystem.dto.request.PlanRequestDTO;
import miu.edu.badgesystem.model.Plan;

public class PlanDataUtil {


    public static Plan setPlan(PlanRequestDTO planRequestDTO) {
        Plan planToSave = new Plan();
        planToSave.setName(planRequestDTO.getName());
        planToSave.setDescription(planRequestDTO.getDescription());
        planToSave.setCount(planRequestDTO.getCount());
        planToSave.setIsLimited(planRequestDTO.getIsLimited());
        planToSave.setStatus('Y');
        return planToSave;
    }
}
