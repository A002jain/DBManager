package com.my.service;

import com.my.service.db.api.config.DbConfig;
import com.my.service.db.api.verticles.DBVertical;
import io.vertx.core.Vertx;

public class App {

    public static void main(String[] args) {
        DbConfig.intiSessionFactory();
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new DBVertical());
    }

/*
Todo: setup hibernate orm
initial
    table creation : done
    read and a read all api: done
    write api: done
    update api: done
    delete api: done
    query api:
    table/schema api:

generify
    add user password
    generify read
    generify create/update
    generify delete

 */
}
