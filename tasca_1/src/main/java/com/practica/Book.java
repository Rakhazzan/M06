package com.practica;

import org.json.JSONObject;

public class Book {
    private String title;
    private String author;

    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("titol", title);
        json.put("autor", author);
        return json.toString();
    }
}