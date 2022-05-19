package miu.edu.badgesystem.model;

import lombok.*;
import miu.edu.badgesystem.audit.Auditable;
import miu.edu.badgesystem.listener.TransactionEntityListener;
import miu.edu.badgesystem.model.enums.TransactionStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(TransactionEntityListener.class)
public class Transaction extends Auditable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "membership_id")
    private Membership membership;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column
    private Character status;

    @Column
    private String transactionNumber;

    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

}
