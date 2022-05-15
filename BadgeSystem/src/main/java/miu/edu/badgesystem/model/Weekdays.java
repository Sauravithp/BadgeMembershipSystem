package miu.edu.badgesystem.model;

import javax.persistence.*;

@Entity
public class Weekdays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private Character status;

}
