package miu.edu.transactionmanagementsystem.listener;


import miu.edu.transactionmanagementsystem.config.Action;
import miu.edu.transactionmanagementsystem.history.TransactionHistory;
import miu.edu.transactionmanagementsystem.model.Transaction;
import miu.edu.transactionmanagementsystem.util.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static miu.edu.transactionmanagementsystem.config.Action.*;

public class TransactionEntityListener {
    @PrePersist
    public void prePersist(Transaction target) {perform(target, INSERTED);}

    @PreUpdate
    public void preUpdate(Transaction target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(Transaction target) {
        perform(target, DELETED);
    }

    @Transactional(MANDATORY)
    public void perform(Transaction target, Action action) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new TransactionHistory(target, action));
    }
}
