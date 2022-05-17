package miu.edu.badgesystem.history;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miu.edu.badgesystem.config.Action;
import miu.edu.badgesystem.model.Badge;
import miu.edu.badgesystem.model.Member;
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
public class MemberHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id",foreignKey = @ForeignKey(name = "FK_member_history_member"))
    private Member member;

    @Column(name = "member_content")
    @Lob
    private String memberContent;

    @CreatedBy
    private Long modifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    @Enumerated(STRING)
    private Action action;

    public MemberHistory(Member member, Action action) {
        this.member = member;
        this.memberContent = member.toString();
        this.action = action;
    }
}
