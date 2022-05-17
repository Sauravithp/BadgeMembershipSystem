package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.MemberRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRolesRepository extends JpaRepository<MemberRoles, Long> {

}
