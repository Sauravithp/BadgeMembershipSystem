package com.example.persistence.model;

import javax.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "description")
    private String description;

    @Column(name = "count")
    private Integer count;

    @Column
    private Character status;

    @Column
    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @OneToOne
    @JoinColumn(name = "location_date_id")
    private LocationDate locationDate;
}
