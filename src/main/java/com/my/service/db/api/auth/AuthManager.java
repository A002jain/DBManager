package com.my.service.db.api.auth;

import io.vertx.core.Future;
import io.vertx.ext.auth.authentication.AuthenticationProvider;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.handler.AuthenticationHandler;
import io.vertx.ext.web.handler.BasicAuthHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthManager {

  private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);

  private AuthManager(){}

  public static AuthenticationHandler basicAuth(){
    AuthenticationProvider authenticationProvider = (credentials, resultHandler) -> {
      UsernamePasswordCredentials ogcredentials = new UsernamePasswordCredentials("test","test");
      UsernamePasswordCredentials credentials1 = new UsernamePasswordCredentials(credentials);
      if(credentials1.getPassword().equals(ogcredentials.getPassword()) && credentials1.getUsername().equals(ogcredentials.getUsername())) {
        logger.info("Login success");
        resultHandler.handle(Future.succeededFuture());
      }else {
        logger.info("Login failure");
        resultHandler.handle(Future.failedFuture("UnAuthorize"));
      }
    };
    return BasicAuthHandler.create(authenticationProvider);
  }
}
