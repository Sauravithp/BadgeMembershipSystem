package com.example.enity.listener;

import com.example.enity.config.Action;
import com.example.enity.history.BadgeHistory;
import com.example.enity.model.Badge;
import com.example.enity.util.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static com.example.enity.config.Action.*;
import static javax.transaction.Transactional.TxType.MANDATORY;

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
