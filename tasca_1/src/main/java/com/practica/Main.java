package com.practica;

public class Main {
    public static void main(String[] args) {
        // Connect to MongoDB before showing the menu
        MongoDBConnectionManager.connect("mongodb+srv://1rdedam:12345@cluster0.jrpeb.mongodb.net/", "Biblioteca");
         // Show the menu
         Menu.mostrarMenu();
       
        
        // Close the connection after menu operations are complete
        MongoDBConnectionManager.closeConnection();
        
    }
}