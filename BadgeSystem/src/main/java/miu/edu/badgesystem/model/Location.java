package miu.edu.badgesystem.model;

import lombok.Data;
import miu.edu.badgesystem.audit.Auditable;

import javax.persistence.*;

@Entity
@Data
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
