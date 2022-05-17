package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.emailAddress=:emailAddress")
    Member getMemberByName(@Param("emailAddress") String emailAddress);
}
