package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LocationDateRepository extends JpaRepository<LocationDate,Long> {

    @Query("SELECT l FROM LocationDate l WHERE l.location.id=:id AND l.status<>'D'")
    LocationDate getLocationDateByLocationId(@Param("id") Long id);


    @Query("SELECT l FROM LocationDate l WHERE l.id=:id AND l.status<>'D'")
    LocationDate getLocationDateById(@Param("id") Long id);

    @Query(" Select count(t.id) from LocationDate t where CURRENT_DATE BETWEEN t.startDate AND t.endDate AND t.id=:id")
    Integer checkIfLocationDateIsAvailable(@Param("id") Long id);
}
