package ro.uaic.info.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository<T> {
    @PersistenceContext
    protected EntityManager entityManager;
    protected EntityTransaction entityTransaction;

    protected Class<T> entityClass;

    protected AbstractRepository() {
        entityTransaction = entityManager.getTransaction();
    }

    public void create(T entity) {
        beginTransaction();
        entityManager.persist(entity);
        commitTransaction();
    }

    protected void beginTransaction() {
        try {
            entityTransaction.begin();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void commitTransaction() {
        try {
            entityTransaction.commit();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void rollbackTransaction() {
        try {
            entityTransaction.rollback();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}