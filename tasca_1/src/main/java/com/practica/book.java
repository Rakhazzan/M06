package com.practica;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class book {
    private ObjectId id;
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private List<String> genres;
    private String description;
    private List<String> keywords;
    private Date dateAdded;
    private String status;
    private List<Rating> ratings;

    // Constructor
    public book(String isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genres = new ArrayList<>();
        this.keywords = new ArrayList<>();
        this.dateAdded = new Date();
        this.status = "available";
        this.ratings = new ArrayList<>();
    }

    // Getters
    public ObjectId getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public String getStatus() {
        return status;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    // Setters
    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    // Helper methods for genres
    public void addGenre(String genre) {
        if (!this.genres.contains(genre)) {
            this.genres.add(genre);
        }
    }

    public void removeGenre(String genre) {
        this.genres.remove(genre);
    }

    // Helper methods for keywords
    public void addKeyword(String keyword) {
        if (!this.keywords.contains(keyword)) {
            this.keywords.add(keyword);
        }
    }

    public void removeKeyword(String keyword) {
        this.keywords.remove(keyword);
    }

    // Helper methods for ratings
    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        return ratings.stream()
                .mapToDouble(Rating::getScore)
                .average()
                .orElse(0.0);
    }

    // MongoDB Document conversion methods
    public Document toDocument() {
        return new Document("isbn", isbn)
                .append("title", title)
                .append("author", author)
                .append("publicationYear", publicationYear)
                .append("genres", genres)
                .append("description", description)
                .append("keywords", keywords)
                .append("dateAdded", dateAdded)
                .append("status", status)
                .append("ratings", ratings.stream()
                        .map(Rating::toDocument)
                        .collect(Collectors.toList()));
    }

    public static Book fromDocument(Document doc) {
        Book book = new Book(
            doc.getString("isbn"),
            doc.getString("title"),
            doc.getString("author"),
            doc.getInteger("publicationYear")
        );
        
        book.setId(doc.getObjectId("_id"));
        book.setGenres(doc.getList("genres", String.class));
        book.setDescription(doc.getString("description"));
        book.setKeywords(doc.getList("keywords", String.class));
        book.setDateAdded(doc.getDate("dateAdded"));
        book.setStatus(doc.getString("status"));
        
        // Convert ratings from documents to Rating objects
        List<Document> ratingDocs = doc.getList("ratings", Document.class);
        if (ratingDocs != null) {
            List<Rating> ratings = ratingDocs.stream()
                    .map(Rating::fromDocument)
                    .collect(Collectors.toList());
            book.setRatings(ratings);
        }
        
        return book;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", status='" + status + '\'' +
                ", averageRating=" + getAverageRating() +
                '}';
    }
}
