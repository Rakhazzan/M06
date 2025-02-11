package com.practica;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
public class LlibreDAO {
    private static MongoCollection<Document> collection;

    public LlibreDAO(String collectionName) {
        LlibreDAO.collection = MongoDBConnectionManager.getDatabase().getCollection(collectionName);
    }

    public String create(Llibre llibre) {
        Document doc = new Document("isbn", llibre.getIsbn())
                .append("titol", llibre.getTitol())
                .append("autor", llibre.getAutor())
                .append("anyPublicacio", llibre.getAnyPublicacio())
                .append("generes", llibre.getGeneres())
                .append("descripcio", llibre.getDescripcio())
                .append("paraulesClau", llibre.getParaulesClau())
                .append("dataAfegit", new Date())
                .append("estat", llibre.getEstat())
                .append("user", llibre.getUser())
                .append("prestec", llibre.getPrestec());

        collection.insertOne(doc);
        return doc.getObjectId("_id").toString();
    }

    public List<Document> findAll() {
        return collection.find().into(new ArrayList<>());
    }
    public static List<Document> findByDate(String dateStr) {
        Document query = new Document("dataAfegit", 
            new Document("$regex", "^" + dateStr));
        return collection.find(query).into(new ArrayList<>());
    }
}