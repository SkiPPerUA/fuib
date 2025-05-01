package org.example.qaTransactionTeam.backEnd.utils;


import com.mongodb.*;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Arrays;

public class BDMongo {

    private static final Logger logger = Logger.getLogger(BDMongo.class);
    private static MongoClient mongoClient;
    private static BasicDBObject search = new BasicDBObject();
    private static DB db;
    private static DBCollection collection;
    private static String nameBase;

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static BasicDBObject searchParam() {
        return search;
    }

    public static DBCollection getCollection() {
        return collection;
    }

    public static void BDMongo(String nameBD){
        if (nameBD.equals("vmtdb")){
            nameBase = "vmtdb";
            connToVmtDB();
        }else if (nameBD.equals("inbtwnrdb")){
            nameBase = "inbtwnrdb";
            connToInbtwnrdb();
        }else {
            System.out.println("Не корректный нейминг бд");
        }
    }

    private static void connToVmtDB(){
        MongoCredential credential = MongoCredential.createCredential("vmt", "vmtdb", Configs1.MONGO_DB_PASSWORD);
        mongoClient = new MongoClient(new ServerAddress(Configs1.MONGO_DB_HOST, Configs1.MONGO_DB_PORT), Arrays.asList(credential));
        db = mongoClient.getDB("vmtdb");
        logger.info("Открыт коннект к базе "+nameBase);
    }

    private static void connToInbtwnrdb(){
        MongoCredential credential = MongoCredential.createCredential("inbtwnr", "inbtwnrdb", Configs1.MONGO_DB_PASSWORD);
        mongoClient = new MongoClient(new ServerAddress(Configs1.MONGO_DB_HOST, Configs1.MONGO_DB_PORT), Arrays.asList(credential));
        db = mongoClient.getDB("inbtwnrdb");
        logger.info("Открыт коннект к базе "+nameBase);
    }

    public static void openCollection(String nameCollect){
        collection = db.getCollection(nameCollect);
        logger.info("Открыта коллекция - "+nameCollect);
    }

    public static void closeConn() throws SQLException {

        mongoClient.close();
        logger.info("Закрыт коннект к базе "+nameBase);

    }


}
