package com.practica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        MongoDBConnectionManager.llibreDAO llibreDAO = new MongoDBConnectionManager.llibreDAO("llibres");

        while (true) {
            System.out.println("1. Afegir llibre");
            System.out.println("2. Mostrar llibres");
            System.out.println("3. Cercar per ID");
            System.out.println("4. Actualitzar llibre");
            System.out.println("5. Eliminar llibre");
            System.out.println("6. Cercar per data");  // Nueva opción
            System.out.println("7. Sortir");
            System.out.print("Opció: ");
            int opcio = scanner.nextInt(); scanner.nextLine();

            switch (opcio) {
                case 1 -> {
                    System.out.print("Títol: ");
                    String titol = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Any de publicació: ");
                    int anyPublicacio = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Gèneres (separats per comes): ");
                    String generesInput = scanner.nextLine();
                    List<String> generes = List.of(generesInput.split(","));
                    System.out.print("Descripció: ");
                    String descripcio = scanner.nextLine();
                    System.out.print("Paraules clau (separades per comes): ");
                    String paraulesInput = scanner.nextLine();
                    List<String> paraulesClau = List.of(paraulesInput.split(","));
                    System.out.print("Estat: ");
                    String estat = scanner.nextLine();

                    Llibre nouLlibre = new Llibre();
                    nouLlibre.setTitol(titol);
                    nouLlibre.setAutor(autor);
                    nouLlibre.setIsbn(isbn);
                    nouLlibre.setAnyPublicacio(anyPublicacio);
                    nouLlibre.setGeneres(new ArrayList<>(generes));
                    nouLlibre.setDescripcio(descripcio);
                    nouLlibre.setParaulesClau(new ArrayList<>(paraulesClau));
                    nouLlibre.setDataAfegit(new Date());
                    nouLlibre.setEstat(estat);

                    llibreDAO.create(nouLlibre);
                    System.out.println("Llibre afegit!");
                }
                case 2 -> {
                    for (Llibre llibre : llibreDAO.findAll()) {
                        System.out.println(llibre.getTitol() + " - " + llibre.getAutor());
                    }
                }
                case 3 -> {
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    Llibre llibre = llibreDAO.findById(id);
                    if (llibre != null) {
                        System.out.println("Títol: " + llibre.getTitol());
                        System.out.println("Autor: " + llibre.getAutor());
                        System.out.println("ISBN: " + llibre.getIsbn());
                        System.out.println("Any de publicació: " + llibre.getAnyPublicacio());
                        System.out.println("Gèneres: " + llibre.getGeneres());
                        System.out.println("Descripció: " + llibre.getDescripcio());
                        System.out.println("Paraules clau: " + llibre.getParaulesClau());
                        System.out.println("Estat: " + llibre.getEstat());
                    } else {
                        System.out.println("No trobat!");
                    }
                }
                case 4 -> {
                    System.out.print("ID: ");
                    String idUpdate = scanner.nextLine();
                    System.out.print("Nou títol: ");
                    String nouTitol = scanner.nextLine();
                    System.out.print("Nou autor: ");
                    String nouAutor = scanner.nextLine();
                    System.out.print("Nou estat: ");
                    String nouEstat = scanner.nextLine();

                    Llibre llibreUpdate = new Llibre();
                    llibreUpdate.setTitol(nouTitol);
                    llibreUpdate.setAutor(nouAutor);
                    llibreUpdate.setEstat(nouEstat);

                    llibreDAO.update(idUpdate, llibreUpdate);
                    System.out.println("Llibre actualitzat!");
                }
                case 5 -> {
                    System.out.print("ID: ");
                    String idDelete = scanner.nextLine();
                    llibreDAO.delete(idDelete);
                    System.out.println("Llibre eliminat!");
                }
                case 6 -> {
                    System.out.print("📅 Introdueix la data (format: YYYY-MM-DD): ");
                    String dataStr = scanner.nextLine();

                    List<Llibre> llibres = llibreDAO.findByDate(dataStr);

                    if (llibres.isEmpty()) {
                        System.out.println("❌ No s'han trobat llibres per aquesta data.");
                    } else {
                        System.out.println("📚 Llibres trobats:");
                        for (Llibre llibre : llibres) {
                            System.out.println("Títol: " + llibre.getTitol());
                            System.out.println("Autor: " + llibre.getAutor());
                            System.out.println("Data afegit: " + llibre.getDataAfegit());
                            System.out.println("------------------------");
                        }
                    }
                }
                case 7 -> {
                    System.out.println("👋 Sortint...");
                    scanner.close();
                    MongoDBConnectionManager.closeConnection();
                    return;
                }
                default -> System.out.println("Opció no vàlida");
            }
        }
    }
}
