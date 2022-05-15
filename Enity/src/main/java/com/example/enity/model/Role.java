package com.example.enity.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column
    private Character status;
}
