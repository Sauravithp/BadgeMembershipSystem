package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.dto.response.LocationResponseDTO;
import miu.edu.badgesystem.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    @Query("SELECT l FROM Location l WHERE l.id=:id AND l.status<>'D'")
    Location getLocationByID(@Param("id") Long id);

    @Query("SELECT l FROM Location l WHERE l.name=:name AND l.status<>'D' AND l.locationType=:locationType")
    Location getLocationByName(@Param("name") String name,@Param("locationType") Enum locationType);

    @Query("SELECT l FROM Location l WHERE l.status<>'D'")
    List<Location> getAllLocation();

    @Query("SELECT l FROM Location l WHERE l.id=:id AND l.status='Y'")
    Optional<Location> getActiveLocationById(@Param("id") Long id);
}
