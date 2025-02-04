package com.practica;

public class Main {
    public static void main(String[] args) {
        MongoDBConnectionManager.connect("mongodb+srv://1rdedam:12345@cluster0.jrpeb.mongodb.net/", "Biblioteca");
        MongoDBConnectionManager.closeConnection();
        Menu.mostrarMenu();
    }
}