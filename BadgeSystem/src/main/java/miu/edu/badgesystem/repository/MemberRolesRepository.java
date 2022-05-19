package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.MemberRoles;
import miu.edu.badgesystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRolesRepository extends JpaRepository<MemberRoles, Long> {

    @Query("SELECT m.role From MemberRoles m where m.member.id=:id")
    List<Role> getRolesByMemberId(@Param("id") Long id);

}
