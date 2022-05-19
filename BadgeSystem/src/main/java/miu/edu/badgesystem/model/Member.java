package miu.edu.badgesystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.badgesystem.audit.Auditable;
import miu.edu.badgesystem.listener.MemberEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(MemberEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member extends Auditable<Long> {

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private List<Badge> badges;


}
