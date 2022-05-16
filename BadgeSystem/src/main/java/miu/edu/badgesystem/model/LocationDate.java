package miu.edu.badgesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "start_date")
    private LocalDate startDate;


    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_time")
    private String startTime;


    @Column(name = "end_time")
    private String endTime;

    @Column
    private Character status;

    @Column
    private Boolean hasTimeSlot;

    @Column
    private Boolean hasLocationClosedDate;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_date_id")
    private List<LocationClosed> locationClosed;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_date_id")
    private List<LocationTimeSlot> locationTimeSlots;

}
