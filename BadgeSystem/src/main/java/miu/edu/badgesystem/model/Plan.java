package miu.edu.badgesystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Plan_id")
    private List<Role> roles;

    @Column(name = "status")
    private Character status;
}
