package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.request.PlanRequestDTO;
import miu.edu.badgesystem.dto.request.RoleRequestDTO;
import miu.edu.badgesystem.dto.response.PlanResponseDTO;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.MemberRoles;
import miu.edu.badgesystem.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberRolesService {

    List<MemberRoles> save(Member member, List<Role> roles);

}
