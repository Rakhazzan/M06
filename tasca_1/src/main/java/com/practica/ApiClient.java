package com.practica;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
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
            .header("Content-Type", "application/json; charset=UTF-8")
            .POST(HttpRequest.BodyPublishers.ofString(book.toJson(), StandardCharsets.UTF_8))
            .build();
        
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
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
                    .header("Accept", "application/json; charset=UTF-8")
                    .GET()
                    .build();

            CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            responseFuture.thenAccept(response -> {
                String responseBody = response.body();
                System.out.println(responseBody);
                System.out.println(".........");

                JSONArray jsonArray = new JSONArray(responseBody);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Book book = new Book(
                        jsonObject.getString("isbn"),
                        jsonObject.getString("titol"),
                        jsonObject.getString("autor"),
                        jsonObject.getInt("anyPublicacio"),
                        jsonObject.getJSONArray("generes").toList(),
                        jsonObject.getString("descripcio"),
                        jsonObject.getJSONArray("paraulesClau").toList(),
                        jsonObject.getString("estat"),
                        jsonObject.getJSONObject("dataAfegit").getString("$date")
                    );
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Book bookForm() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("#############################");
        System.out.println("Add Book form: \n\n");

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        
        System.out.print("Enter publication year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        
        System.out.print("Enter status: ");
        String status = scanner.nextLine();

        return new Book(isbn, title, author, year, List.of(), description, List.of(), status, "");
    }
}