package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.MemberRoles;
import miu.edu.badgesystem.model.Role;
import miu.edu.badgesystem.repository.MemberRolesRepository;
import miu.edu.badgesystem.service.MemberRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MemberRolesServiceImpl implements MemberRolesService {

    @Autowired
    private MemberRolesRepository memberRolesRepository;

    @Override
    public List<MemberRoles> save(Member member, List<Role> roles) {
        List<MemberRoles> memberRoles = new ArrayList<>();

        roles.forEach(role -> {
            MemberRoles memberRoles1 = new MemberRoles();
            memberRoles1.setMember(member);
            memberRoles1.setRole(role);
            memberRoles.add(memberRoles1);
        });

        return memberRolesRepository.saveAll(memberRoles);
    }
}
