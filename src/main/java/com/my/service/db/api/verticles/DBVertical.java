package com.my.service.db.api.verticles;

import com.my.service.db.api.handler.DBApiHandler;
import com.my.service.db.api.auth.AuthManager;
import com.my.service.db.api.handler.RouteManager;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.AuthenticationHandler;
import io.vertx.ext.web.handler.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBVertical  extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(DBVertical.class);
    private static final Integer PORT = 8080;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        AuthenticationHandler authHandler = AuthManager.basicAuth();
        RouteManager routeManager = new RouteManager(new DBApiHandler());
        Router router = Router.router(vertx);

        routeManager.setAuthHandler(router, authHandler);
        routeManager.injectRestRoute(router);
        router.route().failureHandler(ErrorHandler.create(vertx));

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(PORT, http -> {
                    if (http.succeeded()) {
                        startPromise.complete();
                        logger.info("HTTP server started on port {}", PORT);
                        logger.info("Swagger URL= http://localhost:{}/swagger/", PORT);
                    } else {
                        startPromise.fail(http.cause());
                        logger.error("Failed to start HTTP server on port {}", PORT);
                    }
                });

    }

    @Override
    public void stop() throws Exception {
        logger.info("Server Stop {%asctime}");
        super.stop();
    }

}
