package com.my.service.db.api.repository;

import com.my.service.db.api.handler.DBApiHandler;
import com.my.service.db.api.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DBRepository {

    private static final Logger log = LoggerFactory.getLogger(DBApiHandler.class);

    public Object find(String id, Class<?> tableClass){
        try(Session session = HibernateUtil.getSession()) {
            return session.find(tableClass, id);
        }catch (Exception e){
            log.info("Error read All Records", e);
        }
        return null;
    }

    public List findAll(Class<?> TableClass){
        try(Session session = HibernateUtil.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(TableClass);
            Root rootEntry = cq.from(TableClass);
            CriteriaQuery<?> all = cq.select(rootEntry);
            TypedQuery allQuery = session.createQuery(all);
            return allQuery.getResultList();
        }catch (Exception e){
            log.info("Error read All Records", e);
        }
        return null;
    }

    public Object save(Object object){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
            return object;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            log.info("Error saving {}, Rollback changes", object);
        }
        return null;
    }

    public Object saveAll(List<Object> t) {
        return null;
    }

    public Object delete(Object object) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
            return object;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            log.info("Error saving {}, Rollback changes", object);
        }
        return null;
    }

    public Object deleteAll(String id) {
        return null;
    }
}
