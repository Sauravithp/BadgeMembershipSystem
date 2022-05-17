package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

    @Query("SELECT m FROM Member m WHERE m.emailAddress=:emailAddress")
    Member getMemberByEmailAddress(@Param("emailAddress") String emailAddress);

    @Query("SELECT m FROM Member m WHERE m.id=:id AND m.status='Y'")
    Optional<Member> getActiveMemberByID(@Param("id") Long id);

    @Query("SELECT m FROM Member m WHERE  m.status='Y'")
    List<Member> getActiveAllMembers();


    @Query("SELECT m FROM Member m WHERE m.id<>:id AND m.emailAddress=:emailAddress AND m.status='Y'")
    Member getUpdateMemberByName(@Param("emailAddress") String emailAddress, @Param("id") Long id);

}
