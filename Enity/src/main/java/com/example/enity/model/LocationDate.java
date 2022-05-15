package com.example.enity.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
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
    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @OneToMany
    @JoinColumn(name = "location_date_id")
    private List<LocationClosed> locationClosed;

}
