package com.my.service.db.api.util;

import com.my.service.db.api.config.DbConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    /*
    difference between
    sessionFactory.getCurrentSession()
                vs
    sessionFactory.openSession()
     */

    public static Session getSession(){
        SessionFactory sessionFactory = DbConfig.getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        if(session.isOpen()){
//            return session;
//        }
        return sessionFactory.openSession();
    }
}
