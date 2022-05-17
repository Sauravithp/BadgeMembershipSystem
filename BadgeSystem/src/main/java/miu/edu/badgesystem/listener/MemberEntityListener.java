package miu.edu.badgesystem.listener;

import miu.edu.badgesystem.config.Action;
import miu.edu.badgesystem.history.BadgeHistory;
import miu.edu.badgesystem.history.MemberHistory;
import miu.edu.badgesystem.model.Badge;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.util.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static miu.edu.badgesystem.config.Action.*;

public class MemberEntityListener {

    @PrePersist
    public void prePersist(Member target) {perform(target, INSERTED);}

    @PreUpdate
    public void preUpdate(Member target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(Member target) {
        perform(target, DELETED);
    }

    @Transactional(MANDATORY)
    public void perform(Member target, Action action) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new MemberHistory(target, action));
    }
}
