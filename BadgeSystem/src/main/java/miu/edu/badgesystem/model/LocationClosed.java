package miu.edu.badgesystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.badgesystem.audit.Auditable;
import miu.edu.badgesystem.listener.LocationClosedEntityListener;
import miu.edu.badgesystem.listener.LocationEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(LocationClosedEntityListener.class)
public class LocationClosed extends Auditable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date")
    private LocalDate date;

    @Column
    private Character status;

}
