package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.LocationClosed;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.model.LocationTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationClosedRepository extends JpaRepository<LocationClosed,Long> {

    @Query("SELECT t FROM LocationClosed t WHERE t.id=:id AND t.status<>'D'")
    LocationClosed getLocationClosedById(@Param("id") Long id);
}
