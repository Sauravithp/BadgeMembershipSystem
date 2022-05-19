package miu.edu.badgesystem.service;

import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.MemberRoles;
import miu.edu.badgesystem.model.Role;

import java.util.List;

public interface MemberRolesService {

    List<MemberRoles> save(Member member, List<Role> roles);

}
