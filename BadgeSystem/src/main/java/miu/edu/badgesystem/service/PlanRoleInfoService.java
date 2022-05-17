package miu.edu.badgesystem.service;

import miu.edu.badgesystem.model.Plan;
import miu.edu.badgesystem.model.Role;

import java.util.List;

public interface PlanRoleInfoService {

    void save(Plan plan, List<Role> role);
}
