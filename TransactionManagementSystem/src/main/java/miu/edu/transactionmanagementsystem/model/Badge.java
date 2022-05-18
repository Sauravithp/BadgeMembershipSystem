package miu.edu.transactionmanagementsystem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import miu.edu.transactionmanagementsystem.audit.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public class Badge extends Auditable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Character status;

    @Column(name = "badge_number")
    private String badgeNumber;
}
