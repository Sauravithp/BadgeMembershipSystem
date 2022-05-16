package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.LocationClosed;
import miu.edu.badgesystem.model.LocationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationClosedRepository extends JpaRepository<LocationClosed,Long> {
}
