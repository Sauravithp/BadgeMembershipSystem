package miu.edu.badgesystem.history;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.badgesystem.config.Action;
import miu.edu.badgesystem.model.Badge;
import miu.edu.badgesystem.model.Location;
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
public class LocationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id",foreignKey = @ForeignKey(name = "FK_location_history_location"))
    private Location location;

    @Column(name = "location_content")
    @Lob
    private String locationContent;

    @CreatedBy
    private Long modifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    @Enumerated(STRING)
    private Action action;

    public LocationHistory(Location location, Action action) {
        this.location = location;
        this.locationContent = location.toString();
        this.action = action;
    }
}
