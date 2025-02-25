package com.practica;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiClient {
    public static void main(String[] args) {
        Book book = bookForm();
        sendBook(book);
        listAllAsync();
    }

    private static void sendBook(Book book) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:3030/add"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(book.toJson()))
            .build();
        
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void listAllAsync() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:3030/list"))
                    .GET()
                    .build();

            CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            responseFuture.thenAccept(response -> {
                String responseBody = response.body();
                System.out.println(responseBody);
                System.out.println(".........");

                JSONArray jsonArray = new JSONArray(responseBody);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Book book = new Book();
                    book.setTitle(jsonObject.getString("titol"));
                    book.setAuthor(jsonObject.getString("autor"));
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                return null;
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static Book bookForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#############################");
        System.out.println("Add Book form: \n\n");

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        return new Book(title, author);
        
    }
}
