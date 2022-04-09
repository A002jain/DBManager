package com.my.service.db.api.config;

import com.my.service.db.api.model.DemoDOB;
import com.my.service.db.api.model.DemoUser;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class DbConfig {

    private static SessionFactory sessionFactory = null;

    public static void intiSessionFactory(){
        try{
            if(sessionFactory == null) {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, System.getenv("JDBC_DRIVER"));
                properties.put(Environment.URL,"jdbc:" + System.getenv("DATABASEURL"));
//                properties.put(Environment.URL, "jdbc:postgres://ec2-34-231-63-30.compute-1.amazonaws.com:5432/d2jkid44nsf4q3");
                properties.put(Environment.USER, System.getenv("USER"));
                properties.put(Environment.PASS, System.getenv("PASSWORD"));
                properties.put(Environment.DIALECT, System.getenv("JDBC_DIALECT"));
//                properties.put("ssl", System.getenv("ssl"));
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, System.getenv("SESSION_CONTEXT"));
                properties.put(Environment.HBM2DDL_AUTO, System.getenv("TABLE"));
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(DemoUser.class);
                configuration.addAnnotatedClass(DemoDOB.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(){
        try{
            if(sessionFactory == null) {
                intiSessionFactory();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
