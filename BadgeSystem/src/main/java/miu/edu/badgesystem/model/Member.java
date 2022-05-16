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

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private List<Membership> memberships;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private List<Badge> badges;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private List<MemberRoles> memberRoles;

}
