package miu.edu.badgesystem.history;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.badgesystem.config.Action;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;
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
public class LocationDateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_date_id")
    private LocationDate locationDate;

    @Column(name = "location_date_content")
    @Lob
    private String locationDateContent;

    @CreatedBy
    private Long modifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    @Enumerated(STRING)
    private Action action;

    public LocationDateHistory(LocationDate locationDate, Action action) {
        this.locationDate = locationDate;
        this.locationDateContent = locationDate.toString();
        this.action = action;
    }
}
