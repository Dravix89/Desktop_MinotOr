package com.minotor.desktop_minotor.service;

import com.minotor.desktop_minotor.model.PageVisit;
import com.minotor.desktop_minotor.service.MongoDBConnector;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TrackingService {
    private final MongoDatabase db;

    public TrackingService() {
        MongoDBConnector connector = new MongoDBConnector(); // crée une instance
        this.db = connector.getDatabase("minotor_db"); // récupère la base
    }

    public void logPageVisit(String pageName) {
        MongoCollection<Document> collection = db.getCollection("visits");
        Document doc = new Document()
                .append("pageName", pageName)
                .append("visitDate", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        collection.insertOne(doc);
    }

    public List<PageVisit> getAllVisits() {
        MongoCollection<Document> collection = db.getCollection("visits");
        List<PageVisit> visits = new ArrayList<>();
        for (Document doc : collection.find()) {
            String pageName = doc.getString("pageName");
            LocalDateTime visitDate = LocalDateTime.parse(doc.getString("visitDate"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            visits.add(new PageVisit(pageName, visitDate));
        }
        return visits;
    }
}

