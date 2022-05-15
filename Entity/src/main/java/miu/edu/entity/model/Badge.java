package miu.edu.entity.model;

import miu.edu.entity.audit.Auditable;
import miu.edu.entity.listener.BadgeEntityListener;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@EntityListeners(BadgeEntityListener.class)
@Getter
@Setter
@ToString
public class Badge  extends Auditable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Character status;
}
