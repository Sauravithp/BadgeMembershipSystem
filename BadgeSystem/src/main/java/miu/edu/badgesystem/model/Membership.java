package miu.edu.badgesystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @Column(name = "end_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    @Column
    private Character status;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
