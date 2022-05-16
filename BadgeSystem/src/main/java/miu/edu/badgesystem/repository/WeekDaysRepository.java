package miu.edu.badgesystem.repository;

import miu.edu.badgesystem.model.Weekdays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekDaysRepository extends JpaRepository<Weekdays,Long> {

    @Query("SELECT w FROM Weekdays w WHERE w.name=:name")
    Weekdays getWeekDaysByName(@Param("name") String name);
}
