package com.example.starter;

import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


public class MainVerticle extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

  @Override
  public void start(Promise<Void> startPromise){

    Router router = Router.router(vertx);

    router.route("/hello").handler(context -> {
      context.response().end("hello");
    });
    
    router.route().handler(context -> {
      context.response().end("All");
    });
    
    String port = System.getenv("PORT");
    if(port == null)
    	port = "8888";
    vertx.createHttpServer()
     
      .requestHandler(router)
      
      .listen(Integer.parseInt(port),"0.0.0.0",http -> {
      if (http.succeeded()) {
        startPromise.complete();
        logger.info("HTTP server started on port 8888");
        logger.info("URL= http://localhost:8888/");
      } else {
        logger.error("Failed to start HTTP server on port 8888");
        startPromise.fail(http.cause());
      }
    });
  }

}
