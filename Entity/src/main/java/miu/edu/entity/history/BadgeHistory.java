package miu.edu.entity.history;

import miu.edu.entity.config.Action;
import miu.edu.entity.model.Badge;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class BadgeHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "badge_id",foreignKey = @ForeignKey(name = "FK_badge_history_badge"))
    private Badge badge;

    @Column(name = "admin_content")
    @Lob
    private String adminContent;

    @CreatedBy
    private Long modifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    @Enumerated(STRING)
    private Action action;

    public BadgeHistory(Badge badge, Action action) {
        this.badge = badge;
        this.adminContent = badge.toString();
        this.action = action;
    }
}
