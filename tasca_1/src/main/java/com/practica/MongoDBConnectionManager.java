package com.practica;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnectionManager {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void connect(String connectionString, String databaseName) {
        if (mongoClient == null) {
            try {
                ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .serverApi(serverApi)
                        .build();
                mongoClient = MongoClients.create(settings);
                database = mongoClient.getDatabase(databaseName);
                System.out.println("Conectado a MongoDB!");
            } catch (MongoException e) {
                throw new RuntimeException("MongoDB connection failed", e);
            }
        }
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión cerrada.");
        }
    }

    public static MongoDatabase getDatabase() {
        if (database == null) {
            throw new IllegalStateException("La base de datos no está inicializada.");
        }
        return database;
    }

    public static class llibreDAO {
        private MongoCollection<Document> collection;

        public llibreDAO(String collectionName) {
            if (MongoDBConnectionManager.getDatabase() == null) {
                throw new IllegalStateException("La base de datos no está inicializada.");
            }
            this.collection = MongoDBConnectionManager.getDatabase().getCollection(collectionName);
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
                    .append("estat", llibre.getEstat());

            collection.insertOne(doc);
            return doc.getObjectId("_id").toString();
        }

        public Llibre findById(String id) {
            Document doc = collection.find(new Document("_id", new ObjectId(id))).first();
            return doc != null ? documentToLlibre(doc) : null;
        }

        public List<Llibre> findAll() {
            List<Llibre> llibres = new ArrayList<>();
            for (Document doc : collection.find()) {
                llibres.add(documentToLlibre(doc));
            }
            return llibres;
        }

        public long update(String id, Llibre llibre) {
            Document updateDoc = new Document("$set", new Document()
                    .append("titol", llibre.getTitol())
                    .append("autor", llibre.getAutor())
                    .append("estat", llibre.getEstat()));

            return collection.updateOne(
                    new Document("_id", new ObjectId(id)),
                    updateDoc
            ).getModifiedCount();
        }

        public long delete(String id) {
            return collection.deleteOne(new Document("_id", new ObjectId(id))).getDeletedCount();
        }

        public List<Llibre> findByDate(String date) {
            List<Llibre> result = new ArrayList<>();
            try {
                Date parsedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
                Document query = new Document("dataAfegit", new Document("$gte", parsedDate));
                
                for (Document doc : collection.find(query)) {
                    result.add(documentToLlibre(doc));
                }
            } catch (ParseException e) {
                System.out.println("Error al convertir la fecha: " + e.getMessage());
            }
            return result;
        }

        private Llibre documentToLlibre(Document doc) {
            Llibre llibre = new Llibre();
            llibre.setId(doc.getObjectId("_id").toString());
            llibre.setIsbn(doc.getString("isbn"));
            llibre.setTitol(doc.getString("titol"));
            llibre.setAutor(doc.getString("autor"));
            llibre.setAnyPublicacio(doc.getInteger("anyPublicacio"));
            llibre.setGeneres(doc.getList("generes", String.class));
            llibre.setDescripcio(doc.getString("descripcio"));
            llibre.setParaulesClau(doc.getList("paraulesClau", String.class));
            llibre.setDataAfegit(doc.getDate("dataAfegit"));
            llibre.setEstat(doc.getString("estat"));
            return llibre;
        }
    }
}
