package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.LocationTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTimeSlotRepository extends JpaRepository<LocationTimeSlot,Long> {

    @Query("SELECT t FROM LocationTimeSlot t WHERE t.id=:id AND t.status<>'D'")
    LocationTimeSlot getLocationTimeSlotById(@Param("id") Long id);
}
