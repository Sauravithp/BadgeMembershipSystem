package miu.edu.transactionmanagementsystem.history;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.transactionmanagementsystem.config.Action;
import miu.edu.transactionmanagementsystem.model.Transaction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(name = "transaction_content")
    @Lob
    private String transactionContent;

    @CreatedBy
    private Long modifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    @Enumerated(STRING)
    private Action action;

    public TransactionHistory(Transaction transaction, Action action) {
        this.transaction = transaction;
        this.transactionContent = transaction.toString();
        this.action = action;
    }
}
