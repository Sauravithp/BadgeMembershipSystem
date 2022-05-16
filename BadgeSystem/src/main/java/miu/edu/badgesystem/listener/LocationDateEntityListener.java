package miu.edu.badgesystem.listener;

import miu.edu.badgesystem.config.Action;
import miu.edu.badgesystem.history.LocationDateHistory;
import miu.edu.badgesystem.history.LocationHistory;
import miu.edu.badgesystem.model.Location;
import miu.edu.badgesystem.model.LocationDate;
import miu.edu.badgesystem.util.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static miu.edu.badgesystem.config.Action.*;

public class LocationDateEntityListener {
    @PrePersist
    public void prePersist(LocationDate target) {perform(target, INSERTED);}

    @PreUpdate
    public void preUpdate(LocationDate target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(LocationDate target) {
        perform(target, DELETED);
    }

    @Transactional(MANDATORY)
    public void perform(LocationDate target, Action action) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new LocationDateHistory(target, action));
    }
}
