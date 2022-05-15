package miu.edu.entity.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "status")
    private Character status;

    @OneToMany
    @JoinColumn(name = "member_id")
    private List<Membership> memberships;

    @OneToMany
    @JoinColumn(name = "member_id")
    private List<Role> roles;

    @OneToMany
    @JoinColumn(name = "member_id")
    private List<Badge> badges;
}
