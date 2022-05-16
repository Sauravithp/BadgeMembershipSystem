package miu.edu.badgesystem.history;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.badgesystem.config.Action;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationClosed;
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
public class LocationClosedHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_closed_id")
    private LocationClosed locationClosed;

    @Column(name = "location_closed_content")
    @Lob
    private String locationClosedContent;

    @CreatedBy
    private Long modifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    @Enumerated(STRING)
    private Action action;

    public LocationClosedHistory(LocationClosed locationClosed, Action action) {
        this.locationClosed = locationClosed;
        this.locationClosedContent = locationClosed.toString();
        this.action = action;
    }
}
