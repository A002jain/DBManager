package com.my.service.db.api.handler;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.AuthenticationHandler;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

import java.util.HashMap;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class RouteManager {

  private final DBApiHandler apiHandler;
  private static final String READ_ENDPOINT = "/api/table/:table/id/:id/read";
  private static final String READ_ALL_ENDPOINT = "/api/table/:table/read";
  private static final String CREATE_ENDPOINT = "/api/table/:table/create";
  private static final String DELETE_ENDPOINT = "/api/table/:table/id/:id/delete";

  public RouteManager(DBApiHandler apiHandler){
    this.apiHandler = apiHandler;
  }


  public void setAuthHandler(Router router, AuthenticationHandler authHandler){
    router.route("/api/*").handler(authHandler);
  }

  public void injectRestRoute(Router router){
    router.put().handler(BodyHandler.create());
    router.get().handler(StaticHandler.create());
    router.get(READ_ENDPOINT).handler(apiHandler::read);
    router.get(READ_ALL_ENDPOINT).handler(apiHandler::readAll);
    router.put(CREATE_ENDPOINT).handler(apiHandler::create);
    router.delete(DELETE_ENDPOINT).handler(apiHandler::delete);

    router.route().last().handler(routingContext -> {
      HttpServerRequest request = routingContext.request();
      HashMap<String,String> map = new HashMap<>();
      map.put("code", String.valueOf(HTTP_NOT_FOUND));
      map.put("error", "Resource not found");
      map.put("message", String.format(
        "The method %s does not exist. Please make sure you are using a valid endpoint"
        , request.method()));
      routingContext.response().setStatusCode(HTTP_NOT_FOUND).end(Json.encodePrettily(map));
    });
    // work around to stop server when using gradle reload server on save new changes
    router.route("/api/stop").handler(apiHandler::stopServer);
  }
}
