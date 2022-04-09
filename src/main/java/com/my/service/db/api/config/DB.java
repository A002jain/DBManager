package com.my.service.db.api.config;

public class DB {

    private static final String[] db_url = System.getenv("DATABASE_URL").split(":");

    public static String url(){
        String dbType = db_url[0].replace("postgres","postgresql");
        String dbHost = db_url[2].split("@")[1];
        return String.format("jdbc:%s://%s:%s",dbType,dbHost,db_url[3]);
    }

    public static String password(){
        return db_url[2].split("@")[0];
    }

    public static String user(){
        return db_url[1].replace("/","");
    }
}
