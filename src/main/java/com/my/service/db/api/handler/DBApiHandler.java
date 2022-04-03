package com.my.service.db.api.handler;

import com.my.service.db.api.service.DBService;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class DBApiHandler {

  public static final String JSON = "application/json";

  private static final Logger logger = LoggerFactory.getLogger(DBApiHandler.class);
  private static final DBService dbOperation = new DBService();

  public void read(RoutingContext context){
    logger.info("private Hello api called");
    final String table = context.pathParam("table").toUpperCase();
    final String id = context.pathParam("id");
    Object object = dbOperation.find(table, id);

    context.response()
            .putHeader(HttpHeaders.CONTENT_TYPE, JSON)
            .setStatusCode(200)
            .end(Json.encodePrettily(object));
  }

  public void readAll(RoutingContext context){
    final String table = context.pathParam("table").toUpperCase();
    logger.info("read all from table {}", table);
    Object object = dbOperation.findAll(table);
    context.response()
      .setStatusCode(200)
      .end(Json.encodePrettily(object));
  }

  public void create(RoutingContext context){
    final String table = context.pathParam("table").toUpperCase();
    logger.info("create all from table {}", table);
    JsonObject jsonObject = context.getBodyAsJson();
    Object object = dbOperation.save(table, jsonObject);
    context.response()
            .putHeader(HttpHeaders.CONTENT_TYPE, JSON)
            .setStatusCode(200)
            .end(Json.encodePrettily(object));
  }

  public void delete(RoutingContext context){
    final String table = context.pathParam("table").toUpperCase();
    final String id = context.pathParam("id");
    logger.info("removing record from table: {} with id: {}", table, id);
    Object object = dbOperation.delete(table, id);

    context.response()
            .putHeader(HttpHeaders.CONTENT_TYPE, JSON)
            .setStatusCode(200)
            .end(Json.encodePrettily(object));
  }

  public void stopServer(RoutingContext context){
    context.response()
      .setStatusCode(200)
      .end(Json.encodePrettily(Collections.singletonMap("status","stopping server")));
    context.vertx().close();
  }
}
