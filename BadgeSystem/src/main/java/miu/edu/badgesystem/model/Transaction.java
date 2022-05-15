package miu.edu.badgesystem.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;

    @ManyToOne
    @JoinColumn(name = "location_id",nullable = false)
    private Location location;

    @Column
    private Character status;

}
