package miu.edu.badgesystem.listener;

import miu.edu.badgesystem.config.Action;
import miu.edu.badgesystem.history.BadgeHistory;
import miu.edu.badgesystem.model.Badge;
import miu.edu.badgesystem.util.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static miu.edu.badgesystem.config.Action.*;

public class BadgeEntityListener {
    @PrePersist
    public void prePersist(Badge target) {
        perform(target, INSERTED);
    }

    @PreUpdate
    public void preUpdate(Badge target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(Badge target) {
        perform(target, DELETED);
    }

    @Transactional(MANDATORY)
    public void perform(Badge target, Action action) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new BadgeHistory(target, action));
    }
}
