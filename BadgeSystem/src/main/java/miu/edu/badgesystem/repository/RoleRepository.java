package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.name=:name AND r.status='Y'")
    Role getRoleByName(@Param("name") String name);

    @Query("SELECT r FROM Role r WHERE r.id=:id AND r.status='Y'")
    Optional<Role> getActiveRoleByID(@Param("id") Long id);

    @Query("SELECT r FROM Role r WHERE  r.status='Y'")
    List<Role> getActiveAllRoles();
}
