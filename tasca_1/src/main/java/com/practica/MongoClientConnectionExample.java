package com.practica;

import org.bson.Document;
import com.mongodb.client.*;

public class MongoClientConnectionExample {
	  public static void main( String[] args ) {
	        // Replace the placeholder with your MongoDB deployment's connection string
	        String uri = "mongodb+srv://1rdedam:12345@cluster0.jrpeb.mongodb.net";
	        try (MongoClient mongoClient = MongoClients.create(uri)) {
	            MongoDatabase database = mongoClient.getDatabase("Biblioteca");
	            MongoCollection<Document> collection = database.getCollection("llibres");

	            FindIterable<Document> notes = collection.find();
	            for (Document note : notes) {
	                System.out.println(note.toJson());
	            }
	        } catch (Exception e) {
	            System.err.println("Error al connectar o operar amb la base de dades: " + e.getMessage());
	        }
	    }
}
