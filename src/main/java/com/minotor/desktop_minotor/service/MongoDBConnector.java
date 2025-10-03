package com.minotor.desktop_minotor.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnector {

    private static final String URI = "mongodb://localhost:27017";
    private static MongoClient mongoClient;

    public static MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient.getDatabase(dbName);
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    // Méthode pour tester la connexion
    public static boolean testConnection() {
        try {
            MongoDatabase db = getDatabase("test"); // tu peux mettre le nom de ta base ici
            // Essaie de récupérer le premier nom de collection (ou null si aucune)
            db.listCollectionNames().first();
            return true; // Tout s'est bien passé
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Problème de connexion
        }
    }
}
