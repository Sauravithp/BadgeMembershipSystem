package miu.edu.badgesystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "count")
    private Integer count;

    @Column(name = "is_limited")
    private Boolean isLimited;

    @Column(name = "status")
    private Character status;
}
