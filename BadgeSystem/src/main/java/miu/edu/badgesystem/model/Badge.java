package miu.edu.badgesystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import miu.edu.badgesystem.audit.Auditable;
import miu.edu.badgesystem.listener.BadgeEntityListener;
import org.springframework.data.domain.Auditable;

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

    @Column(name = "badge_number")
    private String badgeNumber;
}
