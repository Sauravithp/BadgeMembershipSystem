package miu.edu.badgesystem.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LocationClosed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date")
    private LocalDate date;

    @Column
    private Character status;

}
