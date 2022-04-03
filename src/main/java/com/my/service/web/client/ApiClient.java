package com.my.service.web.client;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApiClient {

  private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);

  private static final Vertx vertx = Vertx.vertx();
  private static final WebClient webClient = WebClient.create(vertx, new WebClientOptions().setDefaultPort(8888));
  private static final RestClient restClient = new RestClient(webClient);
  private static final GraphqlClient graphqlClient = new GraphqlClient(webClient);

  public static final String RESPONSE = "response = {}";
  public static final String ERROR = "Error = {}";

  public static RestClient restClient(){
    return restClient;
  }

  public static GraphqlClient graphqlClient(){ return graphqlClient; }

  ////////////////////////////////////////////////////////////////////////////////////////
//
//  private Credentials getBasicAuth(){
//    return new UsernamePasswordCredentials("test","test");
//  }
//
//  public void withBasicAuth(HttpRequest<Buffer> httpRequest){
//    httpRequest.authentication(getBasicAuth());
//  }
//
//  public void send(HttpRequest<Buffer> httpRequest){
//    httpRequest.send()
//      .onSuccess(res -> logger.info(RESPONSE, res.bodyAsJsonObject().encodePrettily()))
//      .onFailure(res -> logger.info(ERROR, res.getMessage()));
//  }
//
//  public void get(String path){
//    webClient.get(path)
//      .authentication(getBasicAuth())
//      .send()
//      .onSuccess(res -> logger.info(RESPONSE, res.bodyAsJsonObject().encodePrettily()))
//      .onFailure(res -> logger.info(ERROR, res.getMessage()));
//  }
//
//  public void post(String path){
//    webClient.post(path)
//      .authentication(getBasicAuth())
//      .send()
//      .onSuccess(res -> logger.info(RESPONSE, res.bodyAsJsonObject().encodePrettily()))
//      .onFailure(res -> logger.info(ERROR, res.getMessage()));
//  }
//
//  public void put(String path){
//    webClient.put(path)
//      .authentication(getBasicAuth())
//      .send()
//      .onSuccess(res -> logger.info(RESPONSE, res.bodyAsJsonObject().encodePrettily()))
//      .onFailure(res -> logger.info(ERROR, res.getMessage()));
//  }
//
//  public void delete(String path){
//    webClient.delete(path)
//      .authentication(getBasicAuth())
//      .send()
//      .onSuccess(res -> logger.info(RESPONSE, res.bodyAsJsonObject().encodePrettily()))
//      .onFailure(res -> logger.info(ERROR, res.getMessage()));
//  }

}
