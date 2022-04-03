package com.my.service.db.api.service;

import com.my.service.db.api.repository.DBRepository;
import com.my.service.db.api.util.HibernateUtil;
import com.my.service.db.api.model.DemoDOB;
import com.my.service.db.api.model.DemoUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;

public class DBService {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final DBRepository dbRepository = new DBRepository();

    public Object find(String table, String id){
        switch (table) {
            case "USER":
                return dbRepository.find(id,DemoUser.class);
            case "DOB":
                return dbRepository.find(id, DemoDOB.class);
            default:
                return null;
        }
    }

    public List findAll(String table){
        try(Session session = HibernateUtil.getSession()) {
            switch (table.toUpperCase()){
                case "USER":
//                    return session.createQuery("from DemoUser").list();
                    return dbRepository.findAll(DemoUser.class);
                case "DOB":
//                    return session.createQuery("from DemoDOB").list();
                    return dbRepository.findAll(DemoDOB.class);
                default:
                    return null;
            }

        }
    }

    public Object save(String table, JsonObject t){
        Object object;
        HashMap map = (HashMap) t.getMap();
        switch (table.toUpperCase()){
            case "USER":
                object = mapper.convertValue(map, DemoUser.class);
                break;
            case "DOB":
                object = mapper.convertValue(map, DemoDOB.class);
                break;
            default:
                object = null;
        }

        dbRepository.save(object);
        return null;
    }

    public Object saveAll(List<Object> t) {
        return null;
    }

    public Object delete(String table, String id) {
        Object object = find(table, id);
        return dbRepository.delete(object);
    }

    public Object deleteAll(String id) {
        return null;
    }
}
