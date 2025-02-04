package com.practica;

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

    // Connection method
    public static void connect(String connectionString, String databaseName) {
        try {
            ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .serverApi(serverApi)
                    .build();
            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(databaseName);  // ✅ Devuelve MongoDatabase correctamente
            System.out.println("Conectado a MongoDB!");
        } catch (MongoException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
            throw new RuntimeException("MongoDB connection failed", e);
        }
    }
    // Close connection
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    // CRUD for Llibres (Books)
    public static class LlibreDAO {

        private static MongoCollection<Document> collection;

        public LlibreDAO(String collectionName) {
            collection = database.getCollection(collectionName);
        }

        // Create
        public String create(Llibre llibre) {
            Document doc = new Document("isbn", llibre.getIsbn())
                    .append("titol", llibre.getTitol())
                    .append("autor", llibre.getAutor())
                    .append("anyPublicacio", llibre.getAnyPublicacio())
                    .append("generes", llibre.getGeneres())
                    .append("descripcio", llibre.getDescripcio())
                    .append("paraulesClau", llibre.getParaulesClau())
                    .append("dataAfegit", new Date())
                    .append("estat", "disponible");

            collection.insertOne(doc);
            return doc.getObjectId("_id").toString();
        }

        // Read One
        public Llibre findById(String id) {
            Document doc = collection.find(new Document("_id", new ObjectId(id))).first();
            return doc != null ? documentToLlibre(doc) : null;
        }

        // Read All
        public List<Llibre> findAll() {
            List<Llibre> llibres = new ArrayList<>();
            for (Document doc : collection.find()) {
                llibres.add(documentToLlibre(doc));
            }
            return llibres;
        }

        // Update
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

        // Delete
        public long delete(String id) {
            return collection.deleteOne(new Document("_id", new ObjectId(id))).getDeletedCount();
        }

        // Helper method to convert Document to Llibre
        private Llibre documentToLlibre(Document doc) {
            Llibre llibre = new Llibre();
            llibre.setId(doc.getObjectId("_id").toString());
            llibre.setIsbn(doc.getString("isbn"));
            llibre.setTitol(doc.getString("titol"));
            llibre.setAutor(doc.getString("autor"));
            llibre.setAnyPublicacio(doc.getInteger("anyPublicacio"));
            
            // Safe type casting
            List<String> generes = doc.getList("generes", String.class);
            llibre.setGeneres(generes != null ? generes : new ArrayList<>());
            
            llibre.setDescripcio(doc.getString("descripcio"));
            
            // Safe type casting
            List<String> paraulesClau = doc.getList("paraulesClau", String.class);
            llibre.setParaulesClau(paraulesClau != null ? paraulesClau : new ArrayList<>());
            
            llibre.setDataAfegit(doc.getDate("dataAfegit"));
            llibre.setEstat(doc.getString("estat"));
            return llibre;
        }
    }

    public static MongoDatabase getDatabase() {
        return database;  // ✅ Asegúrate de que devuelve un MongoDatabase, no un Object
    }


    }

