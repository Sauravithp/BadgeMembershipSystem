package miu.edu.badgesystem.controller;

import miu.edu.badgesystem.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge,Long> {
}
