package com.practica;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;
class Rating {
    private ObjectId userId;
    private int score;
    private String comment;
    private Date date;

    public Rating(ObjectId userId, int score, String comment) {
        this.userId = userId;
        this.score = score;
        this.comment = comment;
        this.date = new Date();
    }

    // Getters
    public ObjectId getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    // Setters
    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // MongoDB Document conversion methods
    public Document toDocument() {
        return new Document("userId", userId)
                .append("score", score)
                .append("comment", comment)
                .append("date", date);
    }

    public static Rating fromDocument(Document doc) {
        Rating rating = new Rating(
            doc.getObjectId("userId"),
            doc.getInteger("score"),
            doc.getString("comment")
        );
        rating.setDate(doc.getDate("date"));
        return rating;
    }
}