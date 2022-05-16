package miu.edu.badgesystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import miu.edu.badgesystem.audit.Auditable;
import miu.edu.badgesystem.listener.BadgeEntityListener;
import miu.edu.badgesystem.listener.LocationEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(LocationEntityListener.class)
@Getter
@Setter
@ToString
public class Location extends Auditable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity")
    private Integer capacity;

    @Column
    private Character status;

    @Column
    @Enumerated(EnumType.STRING)
    private LocationType locationType;


}
