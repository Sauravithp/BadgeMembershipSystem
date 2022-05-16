package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.dto.response.LocationResponseDTO;
import miu.edu.badgesystem.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    @Query("SELECT l FROM Location l WHERE l.name=:name AND l.status<>'D'")
    Location getLocationByName(@Param("name") String name);

    @Query("SELECT l FROM Location l WHERE l.status<>'D'")
    List<Location> getAllLocation();
}
