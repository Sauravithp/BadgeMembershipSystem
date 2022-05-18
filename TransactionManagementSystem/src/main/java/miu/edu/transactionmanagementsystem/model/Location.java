package miu.edu.transactionmanagementsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import miu.edu.transactionmanagementsystem.audit.Auditable;
import miu.edu.transactionmanagementsystem.model.enums.LocationType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
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
