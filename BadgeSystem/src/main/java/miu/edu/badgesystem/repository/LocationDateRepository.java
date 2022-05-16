package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationDateRepository extends JpaRepository<LocationDate,Long> {

    @Query("SELECT l FROM LocationDate l WHERE l.location.id=:id")
    LocationDate getLocationDateByLocationId(@Param("id") Long id);
}
