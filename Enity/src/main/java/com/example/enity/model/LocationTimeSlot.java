package com.example.enity.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LocationTimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private String startTime;


    @Column(name = "end_time")
    private String endTime;

    @Column
    private Character status;

    @ManyToOne
    @JoinColumn(name = "week_days_id")
    private Weekdays weekdays;
}
